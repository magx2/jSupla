package pl.grzeslowski.jsupla.protocol.structs.scd;

import pl.grzeslowski.jsupla.protocol.PackableProto;
import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceClientCallType;

public interface ServerClientDevice extends Proto, PackableProto {
    ServerDeviceClientCallType callType();
}
