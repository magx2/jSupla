package pl.grzeslowski.jsupla.protocol.structs.cs;

import pl.grzeslowski.jsupla.protocol.PackableProto;
import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.calltypes.ClientServerCallType;

public interface ClientServer extends Proto, PackableProto {
    ClientServerCallType callType();
}
