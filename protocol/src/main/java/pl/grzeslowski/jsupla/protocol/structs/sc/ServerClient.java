package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.calltypes.ServerClientCallType;

public interface ServerClient extends Proto {
    ServerClientCallType callType();
}
