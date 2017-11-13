package pl.grzeslowski.jsupla.server.api;

import reactor.core.publisher.Flux;

public interface Server extends AutoCloseable {
    Flux<? extends Channel> getNewChannelsPipe();

    @Override
    void close();
}
