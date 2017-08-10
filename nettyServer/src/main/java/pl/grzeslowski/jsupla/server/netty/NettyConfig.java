package pl.grzeslowski.jsupla.server.netty;


import pl.grzeslowski.jsupla.Preconditions;

public final class NettyConfig {
    private static final int MAX_PORT_VALUE = 65_535;
    private final int port;

    public NettyConfig(int port) {
        this.port = Preconditions.size(port, 0, MAX_PORT_VALUE);
    }

    public int getPort() {
        return port;
    }
}
