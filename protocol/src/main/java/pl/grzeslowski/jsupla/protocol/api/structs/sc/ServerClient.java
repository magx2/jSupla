package pl.grzeslowski.jsupla.protocol.api.structs.sc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientCallType;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToClientProto;

/**
 * Structs send from server to client.
 */
public interface ServerClient extends FromServerProto, ToClientProto {
    ServerClientCallType callType();
}
