package pl.grzeslowski.jsupla.server;

import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;

public interface SuplaDataPacketDispatcher {
    TSuplaDataPacket dispatch(TSuplaDataPacket dataPacket);
}
