package pl.grzeslowski.jsupla.protocol.structs.sdc;

import pl.grzeslowski.jsupla.protocol.ProtoToSend;
import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceClientCallType;

/**
 * Structs send from server to device/client.
 */
public interface ServerDeviceClient extends ProtoToSend {
    ServerDeviceClientCallType callType();
}
