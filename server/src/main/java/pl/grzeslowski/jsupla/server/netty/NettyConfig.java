package pl.grzeslowski.jsupla.server.netty;

import io.netty.handler.ssl.SslContext;
import lombok.Value;
import reactor.util.annotation.Nullable;

@SuppressWarnings("WeakerAccess")
@Value
public class NettyConfig {
    private static final int MAX_PORT_VALUE = 65_535;
    int port;
    @Nullable
    SslContext sslCtx;
}
