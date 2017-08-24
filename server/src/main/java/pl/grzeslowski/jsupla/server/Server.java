package pl.grzeslowski.jsupla.server;

import reactor.core.publisher.Flux;

public interface Server extends AutoCloseable {
    Flux<SuplaNewConnection> run() throws Exception;
}
