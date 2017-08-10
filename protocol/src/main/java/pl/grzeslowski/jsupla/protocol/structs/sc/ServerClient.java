package pl.grzeslowski.jsupla.protocol.structs.sc;

import pl.grzeslowski.jsupla.protocol.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.calltypes.ServerClientCallType;

public interface ServerClient extends ProtoWithCallType, ProtoWithSize {
    ServerClientCallType callType();
}
