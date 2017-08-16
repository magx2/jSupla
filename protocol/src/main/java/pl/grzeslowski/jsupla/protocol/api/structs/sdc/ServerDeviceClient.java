package pl.grzeslowski.jsupla.protocol.api.structs.sdc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToDeviceClientProto;

/**
 * Structs send from server to device/client.
 */
public interface ServerDeviceClient extends FromServerProto, ToDeviceClientProto {
    ServerDeviceClientCallType callType();
}
