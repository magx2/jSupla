package pl.grzeslowski.jsupla.server.ents.channelandpublisher;

import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.server.ents.channels.FromServerProtoChannel;
import reactor.core.publisher.Flux;

public class ChannelAndFromServerProtoFluxImpl extends ChannelAndFluxImpl<FromServerProtoChannel, FromServerProto>
        implements ChannelAndFromServerProtoFlux {
    public ChannelAndFromServerProtoFluxImpl(final FromServerProtoChannel channel, final Flux<FromServerProto> flux) {
        super(channel, flux);
    }
}
