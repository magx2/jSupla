package pl.grzeslowski.jsupla.protocol.structs.dcs;

import pl.grzeslowski.jsupla.protocol.calltypes.DeviceClientServerCallType;
import pl.grzeslowski.jsupla.protocol.types.FromDeviceClientProto;
import pl.grzeslowski.jsupla.protocol.types.ToServerProto;

/**
 * Structs send from device/client to server.
 */
public interface DeviceClientServer extends FromDeviceClientProto, ToServerProto {
    DeviceClientServerCallType callType();
}
