package pl.grzeslowski.jsupla.server;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

public interface SuplaChannel {
    void write(SuplaDataPacket dataPacket);
}
