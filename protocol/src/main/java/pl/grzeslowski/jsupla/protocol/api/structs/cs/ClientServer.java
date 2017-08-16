package pl.grzeslowski.jsupla.protocol.api.structs.cs;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType;
import pl.grzeslowski.jsupla.protocol.api.types.FromClientProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;

/**
 * Structs send from client to server.
 */
public interface ClientServer extends FromClientProto, ToServerProto {
    ClientServerCallType callType();
}
