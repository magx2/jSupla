package pl.grzeslowski.jsupla.protocol.structs.ds;

import pl.grzeslowski.jsupla.protocol.ProtoToSend;
import pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType;

/**
 * Structs send from device to server.
 */
public interface DeviceServer extends ProtoToSend {
    DeviceServerCallType callType();
}
