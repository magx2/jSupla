package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.server.ents.channels.SuplaDataPacketChannel;

public final class SuplaDataPackageConnection extends SuplaConnection<SuplaDataPacket> {
    public SuplaDataPackageConnection(final SuplaDataPacket suplaDataPacket, final SuplaDataPacketChannel channel) {
        super(suplaDataPacket, channel);
    }
}
