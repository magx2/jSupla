package pl.grzeslowski.jsupla.server.netty.impl;

import io.netty.handler.ssl.SslContext;
import pl.grzeslowski.jsupla.Preconditions;

@SuppressWarnings("WeakerAccess")
public class NettyConfig {
    private static final int MAX_PORT_VALUE = 65_535;
    private final int port;
    private final SslContext sslCtx;

    public NettyConfig(int port, final SslContext sslCtx) {
        this.port = Preconditions.size(port, 0, MAX_PORT_VALUE);
        this.sslCtx = sslCtx;
    }

    public int getPort() {
        return port;
    }

    public SslContext getSslCtx() {
        return sslCtx;
    }
}
