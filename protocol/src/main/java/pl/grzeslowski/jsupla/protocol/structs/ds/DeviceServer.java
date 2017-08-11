package pl.grzeslowski.jsupla.protocol.structs.ds;

import pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType;
import pl.grzeslowski.jsupla.protocol.types.FromDeviceProto;
import pl.grzeslowski.jsupla.protocol.types.ToServerProto;

/**
 * Structs send from device to server.
 */
public interface DeviceServer extends FromDeviceProto, ToServerProto {
    DeviceServerCallType callType();
}
