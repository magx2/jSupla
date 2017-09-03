package pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.mappers;

import pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.ChannelAndRequestFlux;
import pl.grzeslowski.jsupla.server.api.ents.channelandpublisher.ChannelAndToServerProtoFlux;

public interface FromServerProtoToRequest extends
        ChannelAndFluxMapper<ChannelAndToServerProtoFlux, ChannelAndRequestFlux> {
}
