package pl.grzeslowski.jsupla.server;

import org.reactivestreams.Publisher;

public interface Server extends AutoCloseable, Publisher<SupplaNewConnection> {
    void run() throws Exception;
}
