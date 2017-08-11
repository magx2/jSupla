package pl.grzeslowski.jsupla.protocol.structs.sdc;

import pl.grzeslowski.jsupla.protocol.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceClientCallType;

/**
 * Structs send from server to device/client.
 */
public interface ServerDeviceClient extends ProtoWithCallType, ProtoWithSize {
    ServerDeviceClientCallType callType();
}
