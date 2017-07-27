package pl.grzeslowski.jsupla.server;

import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;

import java.util.Optional;

public interface SuplaDataPacketDispatcher {
    Optional<TSuplaDataPacket> dispatch(TSuplaDataPacket dataPacket);
}
