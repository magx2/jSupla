package pl.grzeslowski.jsupla.server.api;

import reactor.core.publisher.Flux;

import java.util.concurrent.ExecutionException;

public interface Server extends AutoCloseable {
    Flux<? extends Channel> getNewChannelsPipe();

    @Override
    void close() throws ExecutionException, InterruptedException;
}
