package pl.grzeslowski.jsupla.server.ents.channelandpublisher;

import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;
import pl.grzeslowski.jsupla.server.ents.channels.FromServerProtoChannel;

public interface ChannelAndToServerProtoFlux extends ChannelAndFlux<FromServerProtoChannel, ToServerProto> {
}
