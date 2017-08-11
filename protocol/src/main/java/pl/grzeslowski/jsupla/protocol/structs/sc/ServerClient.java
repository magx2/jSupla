package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.protocol.calltypes.ServerClientCallType;
import pl.grzeslowski.jsupla.protocol.types.ProtoToSend;

/**
 * Structs send from server to client.
 */
public interface ServerClient extends ProtoToSend {
    ServerClientCallType callType();
}
