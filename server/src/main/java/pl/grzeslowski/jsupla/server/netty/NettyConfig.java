package pl.grzeslowski.jsupla.server.netty;


import static pl.grzeslowski.jsupla.Preconditions.size;

public final class NettyConfig {
    private static final int MAX_PORT_VALUE = 65_535;
    private final int port;

    public NettyConfig(int port) {
        this.port = size(port, 0, MAX_PORT_VALUE);
    }

    public int getPort() {
        return port;
    }
}
