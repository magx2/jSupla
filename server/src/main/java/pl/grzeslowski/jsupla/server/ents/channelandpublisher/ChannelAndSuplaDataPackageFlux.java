package pl.grzeslowski.jsupla.server.ents.channelandpublisher;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.server.ents.channels.SuplaDataPacketChannel;

public interface ChannelAndSuplaDataPackageFlux extends ChannelAndFlux<SuplaDataPacketChannel, SuplaDataPacket> {
}
