package pl.grzeslowski.jsupla.protocol.api.structs.dsc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerClientCallType;
import pl.grzeslowski.jsupla.protocol.api.types.FromDeviceProto;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToClientProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;

/**
 * Structs send from device to server.
 */
public interface DeviceServerClient
        extends FromDeviceProto, ToServerProto, FromServerProto, ToClientProto {
    @Override
    DeviceServerClientCallType callType();
}
