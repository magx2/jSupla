package pl.grzeslowski.jsupla.protocol.structs.ds;

import pl.grzeslowski.jsupla.protocol.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType;

/**
 * Structs send from device to server.
 */
public interface DeviceServer extends ProtoWithCallType, ProtoWithSize {
    DeviceServerCallType callType();
}
