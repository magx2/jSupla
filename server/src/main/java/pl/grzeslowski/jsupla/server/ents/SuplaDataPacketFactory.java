package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.protocol.api.calltypes.CallType;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaDataPacket;

public interface SuplaDataPacketFactory {
    SuplaDataPacket newSuplaDataPacketFactory(byte[] bytes, CallType callType);
}
