package pl.grzeslowski.jsupla.server;

import io.netty.handler.ssl.SslContext;
import jakarta.annotation.Nullable;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("WeakerAccess")
public record NettyConfig(int port, long readTimeoutSeconds, @Nullable SslContext sslCtx) {
    public static final int RANDOM_PORT = 0;
    public static final int SUPLA_HTTP_PORT = 2015;
    public static final int SUPLA_HTTPS_PORT = 2016;
    public static final long DEFAULT_TIMEOUT = TimeUnit.MINUTES.toSeconds(1);

    private static final int MAX_PORT_VALUE = 65_535;

    public NettyConfig(int port) {
        this(port, DEFAULT_TIMEOUT, null);
    }

    public NettyConfig {
        if (port < 0 || port > MAX_PORT_VALUE) {
            throw new IllegalArgumentException("Invalid port value: " + port);
        }
        if (readTimeoutSeconds < 0) {
            throw new IllegalArgumentException(
                    "Invalid read timeout seconds: " + readTimeoutSeconds);
        }
    }
}
