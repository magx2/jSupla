package pl.grzeslowski.jsupla.server.dispatchers;

import pl.grzeslowski.jsupla.proto.structs.TSuplaDataPacket;

import java.util.Optional;

public interface SuplaDataPacketDispatcher {
    Optional<TSuplaDataPacket> dispatch(TSuplaDataPacket dataPacket);
}
