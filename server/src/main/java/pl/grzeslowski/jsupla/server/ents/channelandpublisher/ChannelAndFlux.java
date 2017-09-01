package pl.grzeslowski.jsupla.server.ents.channelandpublisher;

import pl.grzeslowski.jsupla.server.ents.channels.Channel;
import reactor.core.publisher.Flux;

public interface ChannelAndFlux<ChannelT extends Channel<?>, T> {
    ChannelT getChannel();

    Flux<T> getFlux();
}
