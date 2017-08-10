package pl.grzeslowski.jsupla.protocol.structs.cs;

import pl.grzeslowski.jsupla.protocol.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.calltypes.ClientServerCallType;

public interface ClientServer extends ProtoWithCallType, ProtoWithSize {
    ClientServerCallType callType();
}
