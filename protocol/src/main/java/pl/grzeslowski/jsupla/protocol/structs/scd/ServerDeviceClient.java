package pl.grzeslowski.jsupla.protocol.structs.scd;

import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.call_types.ServerDeviceClientCallType;

public interface ServerDeviceClient extends Proto {
    ServerDeviceClientCallType callType();
}
