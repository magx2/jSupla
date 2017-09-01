package pl.grzeslowski.jsupla.server.ents.channelandpublisher;

import pl.grzeslowski.jsupla.server.ents.channels.Channel;
import reactor.core.publisher.Flux;

import static java.util.Objects.requireNonNull;

public class ChannelAndFluxImpl<ChannelT extends Channel<?>, T> implements ChannelAndFlux<ChannelT, T> {
    private final ChannelT channel;
    private final Flux<T> flux;

    public ChannelAndFluxImpl(final ChannelT channel, final Flux<T> flux) {
        this.channel = requireNonNull(channel);
        this.flux = requireNonNull(flux);
    }

    @Override
    public ChannelT getChannel() {
        return channel;
    }

    @Override
    public Flux<T> getFlux() {
        return flux;
    }
}
