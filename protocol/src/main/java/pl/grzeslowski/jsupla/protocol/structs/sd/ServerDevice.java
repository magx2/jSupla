package pl.grzeslowski.jsupla.protocol.structs.sd;

import pl.grzeslowski.jsupla.protocol.ProtoToSend;
import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceCallType;

/**
 * Structs send from server to device.
 */
public interface ServerDevice extends ProtoToSend {
    ServerDeviceCallType callType();
}
