package pl.grzeslowski.jsupla.server.listeners;

import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;

public interface SuplaDataPacketListener {
    void onSuplaDataPacket(TSuplaDataPacket dataPacket);
}
