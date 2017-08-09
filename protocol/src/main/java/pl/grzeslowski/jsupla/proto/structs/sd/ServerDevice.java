package pl.grzeslowski.jsupla.proto.structs.sd;

import pl.grzeslowski.jsupla.proto.Proto;
import pl.grzeslowski.jsupla.proto.consts.CallType;

/**
 * Structs send from server to device (client)
 */
public interface ServerDevice extends Proto {
    CallType callType();
}
