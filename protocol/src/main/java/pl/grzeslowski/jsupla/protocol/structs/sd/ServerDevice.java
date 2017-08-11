package pl.grzeslowski.jsupla.protocol.structs.sd;

import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceCallType;
import pl.grzeslowski.jsupla.protocol.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.types.ToDeviceProto;

/**
 * Structs send from server to device.
 */
public interface ServerDevice extends FromServerProto, ToDeviceProto {
    ServerDeviceCallType callType();
}
