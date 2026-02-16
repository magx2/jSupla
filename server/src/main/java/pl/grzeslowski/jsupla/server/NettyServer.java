package pl.grzeslowski.jsupla.server;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType.SUPLA_DCS_CALL_PING_SERVER;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_PING_SERVER_RESULT;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.ToString;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactoryImpl;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactoryImpl;

@SuppressWarnings("WeakerAccess")
@ToString(onlyExplicitlyIncluded = true)
public final class NettyServer implements AutoCloseable {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);
    private static final int BOSS_THREADS = 1;
    private static final long STARTUP_TIMEOUT_SECONDS = 10;
    private static final long CLOSE_TIMEOUT_SECONDS = 10;

    public static final Set<CallType> NOISY_CALL_TYPES =
            Set.of(SUPLA_DCS_CALL_PING_SERVER, SUPLA_SDC_CALL_PING_SERVER_RESULT);
    public static final Set<Long> NOISY_CALL_TYPE_IDS =
            NOISY_CALL_TYPES.stream().map(CallType::getValue).map(i -> (long) i).collect(toSet());

    @ToString.Include private final NettyConfig nettyConfig;
    private final NioEventLoopGroup bossGroup;
    private final NioEventLoopGroup workerGroup;
    private final ChannelFuture channelFuture;
    private final String instanceId;
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final AtomicBoolean closing = new AtomicBoolean(false);

    public NettyServer(
            NettyConfig nettyConfig,
            CallTypeParser callTypeParser,
            DecoderFactory decoderFactory,
            EncoderFactory encoderFactory,
            MessageHandlerFactory messageHandlerFactory) {
        instanceId = Integer.toHexString(System.identityHashCode(this));
        LOGGER.debug("[{}] New instance {}", instanceId, getClass().getSimpleName());
        this.nettyConfig = requireNonNull(nettyConfig);

        bossGroup = new NioEventLoopGroup(BOSS_THREADS);
        workerGroup = new NioEventLoopGroup();
        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        val nettyServerInitializer =
                new NettyServerInitializer(
                        instanceId,
                        nettyConfig.sslCtx(),
                        nettyConfig.readTimeoutSeconds(),
                        callTypeParser,
                        decoderFactory,
                        encoderFactory,
                        messageHandlerFactory);

        LOGGER.trace("[{}] Configuring server bootstrap", instanceId);
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(nettyServerInitializer)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        channelFuture = bindAndValidate(serverBootstrap);
    }

    public NettyServer(NettyConfig nettyConfig, MessageHandlerFactory messageHandlerFactory) {
        this(
                nettyConfig,
                CallTypeParser.INSTANCE,
                DecoderFactoryImpl.INSTANCE,
                EncoderFactoryImpl.INSTANCE,
                messageHandlerFactory);
    }

    @Override
    public void close() throws ExecutionException, InterruptedException {
        if (closed.get()) {
            LOGGER.debug("[{}] Close already invoked", instanceId);
            return;
        }
        if (!closing.compareAndSet(false, true)) {
            LOGGER.debug("[{}] Close is already in progress", instanceId);
            return;
        }

        try {
            LOGGER.debug("[{}] Closing NettyServer", instanceId);
            boolean serverChannelClosed = closeServerChannel();
            boolean workerClosed = shutdownGroup("workerGroup", workerGroup);
            boolean bossClosed = shutdownGroup("bossGroup", bossGroup);
            boolean fullyClosed = serverChannelClosed && workerClosed && bossClosed;
            if (fullyClosed) {
                closed.set(true);
                LOGGER.debug("[{}] Closed NettyServer", instanceId);
            } else {
                LOGGER.warn(
                        "[{}] Close did not finish cleanly; close() can be retried to finish"
                                + " cleanup",
                        instanceId);
            }
        } finally {
            closing.set(false);
        }
    }

    private ChannelFuture bindAndValidate(ServerBootstrap serverBootstrap) {
        LOGGER.debug("[{}] Binding to port {}", instanceId, nettyConfig.port());
        ChannelFuture bindFuture = serverBootstrap.bind(nettyConfig.port());
        if (!bindFuture.awaitUninterruptibly(STARTUP_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
            shutdownGroup("workerGroup", workerGroup);
            shutdownGroup("bossGroup", bossGroup);
            throw new IllegalStateException(
                    "Cannot bind server to port "
                            + nettyConfig.port()
                            + " within "
                            + STARTUP_TIMEOUT_SECONDS
                            + " seconds");
        }
        if (!bindFuture.isSuccess()) {
            shutdownGroup("workerGroup", workerGroup);
            shutdownGroup("bossGroup", bossGroup);
            throw new IllegalStateException(
                    "Cannot bind server to port " + nettyConfig.port(), bindFuture.cause());
        }
        return bindFuture;
    }

    private boolean closeServerChannel() {
        val channel = channelFuture.channel();
        if (!channel.isOpen()) {
            LOGGER.debug("[{}] Server channel already closed", instanceId);
            return true;
        }
        LOGGER.debug("[{}] Closing server channel", instanceId);
        if (!channel.close().awaitUninterruptibly(CLOSE_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
            LOGGER.warn(
                    "[{}] Timed out while closing server channel, timeoutSeconds={}",
                    instanceId,
                    CLOSE_TIMEOUT_SECONDS);
            return false;
        }
        LOGGER.debug("[{}] Closed server channel", instanceId);
        return true;
    }

    private boolean shutdownGroup(String name, EventLoopGroup group) {
        if (group.isTerminated()) {
            LOGGER.debug("[{}] {} already terminated", instanceId, name);
            return true;
        }
        LOGGER.debug("[{}] Closing {}", instanceId, name);
        group.shutdownGracefully(0, CLOSE_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        if (!group.terminationFuture()
                .awaitUninterruptibly(CLOSE_TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
            LOGGER.warn(
                    "[{}] Timed out while closing {}, timeoutSeconds={}",
                    instanceId,
                    name,
                    CLOSE_TIMEOUT_SECONDS);
            return false;
        }
        LOGGER.debug("[{}] Closed {}", instanceId, name);
        return true;
    }
}
