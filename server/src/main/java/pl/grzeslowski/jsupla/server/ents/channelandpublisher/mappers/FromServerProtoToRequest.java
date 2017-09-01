package pl.grzeslowski.jsupla.server.ents.channelandpublisher.mappers;

import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndRequestFlux;
import pl.grzeslowski.jsupla.server.ents.channelandpublisher.ChannelAndToServerProtoFlux;

public interface FromServerProtoToRequest extends
        ChannelAndFluxMapper<ChannelAndToServerProtoFlux, ChannelAndRequestFlux> {
}
