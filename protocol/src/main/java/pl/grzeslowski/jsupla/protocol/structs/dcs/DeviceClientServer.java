package pl.grzeslowski.jsupla.protocol.structs.dcs;

import pl.grzeslowski.jsupla.protocol.calltypes.DeviceClientServerCallType;
import pl.grzeslowski.jsupla.protocol.types.ProtoToSend;

/**
 * Structs send from device/client to server.
 */
public interface DeviceClientServer extends ProtoToSend {
    DeviceClientServerCallType callType();
}
