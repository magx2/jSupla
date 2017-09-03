package pl.grzeslowski.jsupla.server.impl.ents.channelandpublisher;

import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;
import pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.ChannelAndToServerProtoFlux;
import pl.grzeslowski.jsupla.server.api.ents.channels.FromServerProtoChannel;
import reactor.core.publisher.Flux;

public class ChannelAndToServerProtoFluxImpl extends ChannelAndFluxImpl<FromServerProtoChannel, ToServerProto>
        implements ChannelAndToServerProtoFlux {
    public ChannelAndToServerProtoFluxImpl(final FromServerProtoChannel channel, final Flux<ToServerProto> flux) {
        super(channel, flux);
    }
}
