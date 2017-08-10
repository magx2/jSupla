package pl.grzeslowski.jsupla.protocol.structs.ds;

import pl.grzeslowski.jsupla.protocol.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType;

public interface DeviceServer extends ProtoWithCallType, ProtoWithSize {
    DeviceServerCallType callType();
}
