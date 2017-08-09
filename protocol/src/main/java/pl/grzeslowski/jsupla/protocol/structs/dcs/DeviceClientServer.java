package pl.grzeslowski.jsupla.protocol.structs.dcs;

import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.calltypes.DeviceClientServerCallType;

public interface DeviceClientServer extends Proto {
    DeviceClientServerCallType callType();
}
