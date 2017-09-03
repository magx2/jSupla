package pl.grzeslowski.jsupla.server.api;

import pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.ChannelAndRequestFlux;
import reactor.core.publisher.Flux;

public interface SuplaStream {
    Flux<ChannelAndRequestFlux> fullStream();
}
