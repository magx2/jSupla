package pl.grzeslowski.jsupla.proto.structs.ds;

import pl.grzeslowski.jsupla.consts.CallType;
import pl.grzeslowski.jsupla.proto.Proto;

/**
 * Structs send from device (client) to server
 */
public interface DeviceServer extends Proto {
    CallType callType();
}
