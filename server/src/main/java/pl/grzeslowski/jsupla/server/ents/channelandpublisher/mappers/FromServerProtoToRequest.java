package pl.grzeslowski.jsupla.server.ents.channelandpublisher.mappers;

import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndFromServerProtoFlux;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndRequestFlux;

public interface FromServerProtoToRequest extends
        ChannelAndFluxMapper<ChannelAndFromServerProtoFlux, ChannelAndRequestFlux> {
}
