package pl.grzeslowski.jsupla.protocol.structs.cs;

import pl.grzeslowski.jsupla.protocol.calltypes.ClientServerCallType;
import pl.grzeslowski.jsupla.protocol.types.ProtoToSend;

/**
 * Structs send from client to server.
 */
public interface ClientServer extends ProtoToSend {
    ClientServerCallType callType();
}
