package pl.grzeslowski.jsupla.protocol.structs.ds;

import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.call_types.DeviceServerCallType;

/**
 * Structs send from device (client) to server
 */
public interface DeviceServer extends Proto {
    DeviceServerCallType callType();
}