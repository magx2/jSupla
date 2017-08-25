package pl.grzeslowski.jsupla.server;

import pl.grzeslowski.jsupla.server.ents.SuplaNewConnection;
import reactor.core.publisher.Flux;

public interface Server extends AutoCloseable {
    Flux<SuplaNewConnection> run() throws Exception;
}
