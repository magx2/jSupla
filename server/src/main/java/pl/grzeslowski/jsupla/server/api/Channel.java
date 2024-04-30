package pl.grzeslowski.jsupla.server.api;

import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface Channel extends AutoCloseable {
    Flux<ToServerProto> getMessagePipe();

    Flux<LocalDateTime> write(Flux<FromServerProto> fromServerEntityFlux);

    @Override
    void close();
}
