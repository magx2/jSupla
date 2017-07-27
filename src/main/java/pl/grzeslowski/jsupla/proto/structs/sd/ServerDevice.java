package pl.grzeslowski.jsupla.proto.structs.sd;

import pl.grzeslowski.jsupla.consts.CallType;
import pl.grzeslowski.jsupla.proto.Proto;

/**
 * Structs send from server to device (client)
 */
public interface ServerDevice extends Proto {
    CallType callType();
}
