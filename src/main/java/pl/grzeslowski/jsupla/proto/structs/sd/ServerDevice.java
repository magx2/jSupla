package pl.grzeslowski.jsupla.proto.structs.sd;

import pl.grzeslowski.jsupla.consts.CallTypes;
import pl.grzeslowski.jsupla.proto.Proto;

/**
 * Structs send from server to device (client)
 */
public interface ServerDevice extends Proto {
    CallTypes callType();
}
