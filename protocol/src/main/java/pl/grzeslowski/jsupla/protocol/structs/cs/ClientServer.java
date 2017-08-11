package pl.grzeslowski.jsupla.protocol.structs.cs;

import pl.grzeslowski.jsupla.protocol.calltypes.ClientServerCallType;
import pl.grzeslowski.jsupla.protocol.types.FromClientProto;
import pl.grzeslowski.jsupla.protocol.types.ToServerProto;

/**
 * Structs send from client to server.
 */
public interface ClientServer extends FromClientProto, ToServerProto {
    ClientServerCallType callType();
}
