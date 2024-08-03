package pl.grzeslowski.jsupla.server.netty;

import io.netty.handler.ssl.SslContext;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.grzeslowski.jsupla.protocol.api.calltypes.CallTypeParser;
import pl.grzeslowski.jsupla.protocol.api.decoders.DecoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.server.api.MessageHandler;
import pl.grzeslowski.jsupla.server.api.Server;
import pl.grzeslowski.jsupla.server.api.ServerFactory;
import pl.grzeslowski.jsupla.server.api.ServerProperties;

import java.util.function.Supplier;


@RequiredArgsConstructor
public final class NettyServerFactory implements ServerFactory {
    public static final String PORT = "port";
    public static final String SSL_CTX = "sslCtx";

    @NonNull
    private final CallTypeParser callTypeParser;
    @NonNull
    private final DecoderFactory decoderFactory;
    @NonNull
    private final EncoderFactory encoderFactory;

    @Override
    public Server createNewServer(@NonNull ServerProperties serverProperties, @NonNull Supplier<MessageHandler> messageHandlerFactory) {
        return new NettyServer(
            fromServerProperties(serverProperties),
            callTypeParser,
            decoderFactory,
            encoderFactory,
            messageHandlerFactory);
    }

    private NettyConfig fromServerProperties(ServerProperties serverProperties) {
        return new NettyConfig(
            serverProperties.getProperty(PORT, Integer.class),
            serverProperties.getProperty(SSL_CTX, SslContext.class)
        );
    }
}
