package pl.grzeslowski.jsupla.protocol.api.structs.scd;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientDeviceCallType;
import pl.grzeslowski.jsupla.protocol.api.types.FromClientProto;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToClientProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;

/**
 * Structs send from server to client.
 */
public interface ServerClientDevice
        extends FromServerProto, ToClientProto, FromClientProto, ToServerProto {
    @Override
    ServerClientDeviceCallType callType();
}
