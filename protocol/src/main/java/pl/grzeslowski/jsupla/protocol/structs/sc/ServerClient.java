package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.protocol.ProtoToSend;
import pl.grzeslowski.jsupla.protocol.calltypes.ServerClientCallType;

/**
 * Structs send from server to client.
 */
public interface ServerClient extends ProtoToSend {
    ServerClientCallType callType();
}
