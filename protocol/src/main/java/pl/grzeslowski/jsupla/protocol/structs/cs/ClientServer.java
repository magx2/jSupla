package pl.grzeslowski.jsupla.protocol.structs.cs;

import pl.grzeslowski.jsupla.protocol.ProtoToSend;
import pl.grzeslowski.jsupla.protocol.calltypes.ClientServerCallType;

/**
 * Structs send from client to server.
 */
public interface ClientServer extends ProtoToSend {
    ClientServerCallType callType();
}
