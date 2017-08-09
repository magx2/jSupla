package pl.grzeslowski.jsupla.server.dispatchers;

import pl.grzeslowski.jsupla.protocol.structs.SuplaDataPacket;

import java.util.Optional;

public interface SuplaDataPacketDispatcher {
    Optional<SuplaDataPacket> dispatch(SuplaDataPacket dataPacket);
}
