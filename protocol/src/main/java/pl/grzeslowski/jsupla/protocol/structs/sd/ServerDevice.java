package pl.grzeslowski.jsupla.protocol.structs.sd;

import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.consts.CallType;

/**
 * Structs send from server to device (client)
 */
public interface ServerDevice extends Proto {
    CallType callType();
}
