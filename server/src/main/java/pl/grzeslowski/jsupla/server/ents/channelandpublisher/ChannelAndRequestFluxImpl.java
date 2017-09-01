package pl.grzeslowski.jsupla.server.ents.channelandpublisher;

import pl.grzeslowski.jsupla.server.entities.requests.Request;
import pl.grzeslowski.jsupla.server.ents.channels.ResponseChannel;
import reactor.core.publisher.Flux;

public class ChannelAndRequestFluxImpl extends ChannelAndFluxImpl<ResponseChannel, Request>
        implements ChannelAndRequestFlux {
    public ChannelAndRequestFluxImpl(final ResponseChannel channel, final Flux<Request> flux) {
        super(channel, flux);
    }
}
