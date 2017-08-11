package pl.grzeslowski.jsupla.protocol.structs.dcs;

import pl.grzeslowski.jsupla.protocol.ProtoToSend;
import pl.grzeslowski.jsupla.protocol.calltypes.DeviceClientServerCallType;

/**
 * Structs send from device/client to server.
 */
public interface DeviceClientServer extends ProtoToSend {
    DeviceClientServerCallType callType();
}
