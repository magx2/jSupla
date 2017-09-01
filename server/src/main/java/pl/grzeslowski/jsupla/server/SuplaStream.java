package pl.grzeslowski.jsupla.server;

import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndRequestFlux;
import reactor.core.publisher.Flux;

public interface SuplaStream {
    Flux<ChannelAndRequestFlux> fullStream();
}
