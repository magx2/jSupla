package pl.grzeslowski.jsupla.server;

public interface Server extends AutoCloseable {
    void run() throws Exception;
}
