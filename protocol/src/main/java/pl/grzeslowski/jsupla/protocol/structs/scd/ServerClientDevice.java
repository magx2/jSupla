package pl.grzeslowski.jsupla.protocol.structs.scd;

import pl.grzeslowski.jsupla.protocol.ProtoWithCallType;
import pl.grzeslowski.jsupla.protocol.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceClientCallType;

public interface ServerClientDevice extends ProtoWithCallType, ProtoWithSize {
    ServerDeviceClientCallType callType();
}
