package pl.grzeslowski.jsupla.server.dispatchers;

import pl.grzeslowski.jsupla.protocol.structs.TSuplaDataPacket;

import java.util.Optional;

public interface SuplaDataPacketDispatcher {
    Optional<TSuplaDataPacket> dispatch(TSuplaDataPacket dataPacket);
}
