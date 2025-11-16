package pl.grzeslowski.jsupla.protocol.api.structs.sdc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToClientProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToDeviceProto;

/**
 * Structs send from server to device/client.
 */
public interface ServerDeviceClient extends FromServerProto, ToDeviceProto, ToClientProto {
    @Override
    ServerDeviceClientCallType callType();
}
