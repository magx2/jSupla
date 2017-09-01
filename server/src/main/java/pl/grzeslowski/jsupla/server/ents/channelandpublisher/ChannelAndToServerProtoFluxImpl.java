package pl.grzeslowski.jsupla.server.ents.channelandpublisher;

import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;
import pl.grzeslowski.jsupla.server.ents.channels.FromServerProtoChannel;
import reactor.core.publisher.Flux;

public class ChannelAndToServerProtoFluxImpl extends ChannelAndFluxImpl<FromServerProtoChannel, ToServerProto>
        implements ChannelAndToServerProtoFlux {
    public ChannelAndToServerProtoFluxImpl(final FromServerProtoChannel channel, final Flux<ToServerProto> flux) {
        super(channel, flux);
    }
}
