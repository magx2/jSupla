package pl.grzeslowski.jsupla.server.impl.ents.channelandpublisher;

import pl.grzeslowski.jsupla.server.api.entities.requests.Request;
import pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.ChannelAndRequestFlux;
import pl.grzeslowski.jsupla.server.api.ents.channels.ResponseChannel;
import reactor.core.publisher.Flux;

public class ChannelAndRequestFluxImpl extends ChannelAndFluxImpl<ResponseChannel, Request>
        implements ChannelAndRequestFlux {
    public ChannelAndRequestFluxImpl(final ResponseChannel channel, final Flux<Request> flux) {
        super(channel, flux);
    }
}
