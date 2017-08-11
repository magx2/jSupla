package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.protocol.calltypes.ServerClientCallType;
import pl.grzeslowski.jsupla.protocol.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.types.ToClientProto;

/**
 * Structs send from server to client.
 */
public interface ServerClient extends FromServerProto, ToClientProto {
    ServerClientCallType callType();
}
