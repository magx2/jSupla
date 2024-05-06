package pl.grzeslowski.jsupla.protocol.api.structs.scd;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientDeviceCallType;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToClientProto;

/**
 * Structs send from server to client.
 */
public interface ServerClientDevice extends FromServerProto, ToClientProto {
    ServerClientDeviceCallType callType();
}
