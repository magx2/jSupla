package pl.grzeslowski.jsupla.protocol.structs.dcs;

import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.call_types.DeviceClientServerCallType;

public interface DeviceClientServer extends Proto {
    DeviceClientServerCallType callType();
}
