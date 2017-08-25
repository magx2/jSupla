package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.server.SuplaChannel;

public final class SuplaDataPackageConnection extends SuplaConnection<SuplaDataPacket> {
    public SuplaDataPackageConnection(final SuplaDataPacket suplaDataPacket, final SuplaChannel channel) {
        super(suplaDataPacket, channel);
    }
}
