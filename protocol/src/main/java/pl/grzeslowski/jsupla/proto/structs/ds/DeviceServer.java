package pl.grzeslowski.jsupla.proto.structs.ds;

import pl.grzeslowski.jsupla.proto.Proto;
import pl.grzeslowski.jsupla.proto.consts.CallType;

/**
 * Structs send from device (client) to server
 */
public interface DeviceServer extends Proto {
    CallType callType();
}
