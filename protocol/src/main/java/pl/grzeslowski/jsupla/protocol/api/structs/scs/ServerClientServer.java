package pl.grzeslowski.jsupla.protocol.api.structs.scs;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerClientServerCallType;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToClientProto;

/**
 * Structs send from server to client.
 */
public interface ServerClientServer extends FromServerProto, ToClientProto {
    ServerClientServerCallType callType();
}
