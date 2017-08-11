package pl.grzeslowski.jsupla.protocol.structs.sd;

import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceCallType;
import pl.grzeslowski.jsupla.protocol.types.ProtoToSend;

/**
 * Structs send from server to device.
 */
public interface ServerDevice extends ProtoToSend {
    ServerDeviceCallType callType();
}
