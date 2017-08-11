package pl.grzeslowski.jsupla.protocol.structs.sdc;

import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceClientCallType;
import pl.grzeslowski.jsupla.protocol.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.types.ToDeviceClientProto;

/**
 * Structs send from server to device/client.
 */
public interface ServerDeviceClient extends FromServerProto, ToDeviceClientProto {
    ServerDeviceClientCallType callType();
}
