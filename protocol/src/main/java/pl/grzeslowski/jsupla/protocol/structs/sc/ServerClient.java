package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.call_types.ServerClientCallType;

public interface ServerClient extends Proto {
    ServerClientCallType callType();
}
