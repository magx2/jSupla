package pl.grzeslowski.jsupla.protocol.structs.ds;

import pl.grzeslowski.jsupla.protocol.PackableProto;
import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType;

public interface DeviceServer extends Proto, PackableProto {
    DeviceServerCallType callType();
}
