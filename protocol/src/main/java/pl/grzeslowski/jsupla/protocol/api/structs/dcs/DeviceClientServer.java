package pl.grzeslowski.jsupla.protocol.api.structs.dcs;

import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType;
import pl.grzeslowski.jsupla.protocol.api.types.FromDeviceClientProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;

/**
 * Structs send from device/client to server.
 */
public interface DeviceClientServer extends FromDeviceClientProto, ToServerProto {
    DeviceClientServerCallType callType();
}
