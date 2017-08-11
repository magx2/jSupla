package pl.grzeslowski.jsupla.protocol.structs.ds;

import pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType;
import pl.grzeslowski.jsupla.protocol.types.ProtoToSend;

/**
 * Structs send from device to server.
 */
public interface DeviceServer extends ProtoToSend {
    DeviceServerCallType callType();
}
