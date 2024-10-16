package pl.grzeslowski.jsupla.protocol.api.structs.dcs;

import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType;
import pl.grzeslowski.jsupla.protocol.api.types.FromClientProto;
import pl.grzeslowski.jsupla.protocol.api.types.FromDeviceProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToClientProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;

/**
 * Structs send from device/client to server.
 */
public interface DeviceClientServer extends FromDeviceProto, ToClientProto, FromClientProto, ToServerProto {
    DeviceClientServerCallType callType();
}
