package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;
import pl.grzeslowski.jsupla.server.ents.channels.SuplaDataPacketChannel;

import static java.util.Objects.requireNonNull;

public final class SuplaDataPackageChannel {
    private final SuplaDataPacket suplaDataPacket;
    private final SuplaDataPacketChannel channel;

    public SuplaDataPackageChannel(final SuplaDataPacket suplaDataPacket, final SuplaDataPacketChannel channel) {
        this.suplaDataPacket = requireNonNull(suplaDataPacket);
        this.channel = requireNonNull(channel);
    }

    public SuplaDataPacket getSuplaDataPacket() {
        return suplaDataPacket;
    }

    public SuplaDataPacketChannel getChannel() {
        return channel;
    }
}
