package pl.grzeslowski.jsupla.protocol.structs.sd;

import pl.grzeslowski.jsupla.protocol.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceCallType;

/**
 * Structs send from server to device (client).
 */
public interface ServerDevice extends ProtoWithCallType, ProtoWithSize {
    ServerDeviceCallType callType();
}
