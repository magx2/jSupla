package pl.grzeslowski.jsupla.protocol.structs.dcs;

import pl.grzeslowski.jsupla.protocol.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.calltypes.DeviceClientServerCallType;

public interface DeviceClientServer extends ProtoWithCallType, ProtoWithSize {
    DeviceClientServerCallType callType();
}
