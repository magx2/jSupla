package pl.grzeslowski.jsupla.protocol.api.structs.sd;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToDeviceProto;

/**
 * Structs send from server to device.
 */
public interface ServerDevice extends FromServerProto, ToDeviceProto {
    ServerDeviceCallType callType();
}
