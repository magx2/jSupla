package pl.grzeslowski.jsupla.server.api;

import pl.grzeslowski.jsupla.protocoljava.api.types.FromServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.types.ToServerEntity;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface Channel extends AutoCloseable {
    Flux<ToServerEntity> getMessagePipe();

    Flux<LocalDateTime> write(Flux<FromServerEntity> fromServerEntityFlux);

    ChannelDescription getChannelDescription();

    @Override
    void close();
}
