package pl.grzeslowski.jsupla.protocol.structs.cs;

import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.call_types.ClientServerCallType;

public interface ClientServer extends Proto {
    ClientServerCallType callType();
}
