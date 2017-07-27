package pl.grzeslowski.jsupla.proto.structs.ds;

import pl.grzeslowski.jsupla.consts.CallTypes;
import pl.grzeslowski.jsupla.proto.Proto;

/**
 * Structs send from device (client) to server
 */
public interface DeviceServer extends Proto {
    CallTypes callType();
}
