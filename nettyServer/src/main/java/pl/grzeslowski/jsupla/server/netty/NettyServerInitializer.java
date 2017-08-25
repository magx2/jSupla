package pl.grzeslowski.jsupla.server.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.grzeslowski.jsupla.server.ents.SuplaNewConnection;
import reactor.core.publisher.FluxSink;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class NettyServerInitializer extends ChannelInitializer<SocketChannel> implements NotificationAboutNewChannel {
    private final Logger logger = LoggerFactory.getLogger(NettyServerInitializer.class);

    private final SslContext sslCtx;
    private List<FluxSink<SuplaNewConnection>> consumers = Collections.synchronizedList(new LinkedList<>());

    NettyServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }

        // TODO add DelimiterBasedFrameDecoder
        pipeline.addLast(new SuplaDataPacketDecoder());
        pipeline.addLast(new SuplaDataPacketEncoder());

        // and then business logic.
        pipeline.addLast(new SuplaHandler(this));
    }

    void addOnNewConnectionListener(FluxSink<SuplaNewConnection> consumer) {
        logger.info("NettyServerInitializer.addOnNewConnectionListener(consumer)");
        this.consumers.add(consumer);
    }

    void removeOnNewConnectionListener(FluxSink<SuplaNewConnection> emitter) {
        logger.info("NettyServerInitializer.removeOnNewConnectionListener(emitter)");
        this.consumers.remove(emitter);
    }

    @Override
    public void notify(final SuplaNewConnection conn) {
        consumers.forEach(c -> c.next(conn));
    }
}
