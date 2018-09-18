package pl.grzeslowski.jsupla.server.netty.api;

import io.netty.handler.ssl.SslContext;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.server.api.Server;
import pl.grzeslowski.jsupla.server.api.ServerFactory;
import pl.grzeslowski.jsupla.server.api.ServerProperties;
import pl.grzeslowski.jsupla.server.netty.impl.NettyConfig;
import pl.grzeslowski.jsupla.server.netty.impl.NettyServer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public final class NettyServerFactory implements ServerFactory {
    public static final String PORT = "port";
    public static final String SSL_CTX = "sslCtx";

    private final CallTypeParser callTypeParser;
    private final DecoderFactory decoderFactory;
    private final EncoderFactory encoderFactory;

    public NettyServerFactory(final CallTypeParser callTypeParser,
                              final DecoderFactory decoderFactory,
                              final EncoderFactory encoderFactory) {
        this.callTypeParser = requireNonNull(callTypeParser);
        this.decoderFactory = requireNonNull(decoderFactory);
        this.encoderFactory = requireNonNull(encoderFactory);
    }

    @Override
    public Server createNewServer(@NotNull final ServerProperties serverProperties) {
        return new NettyServer(
                fromServerProperties(serverProperties),
                callTypeParser,
                decoderFactory,
                encoderFactory);
    }

    private NettyConfig fromServerProperties(ServerProperties serverProperties) {
        return new NettyConfig(
                serverProperties.getProperty(PORT, Integer.class),
                serverProperties.getProperty(SSL_CTX, SslContext.class)
        );
    }
}
