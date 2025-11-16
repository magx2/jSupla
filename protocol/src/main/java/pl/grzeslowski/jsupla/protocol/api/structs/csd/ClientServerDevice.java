package pl.grzeslowski.jsupla.protocol.api.structs.csd;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerDeviceCallType;
import pl.grzeslowski.jsupla.protocol.api.types.FromClientProto;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToDeviceProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;

/**
 * Structs send from client to server.
 */
public interface ClientServerDevice
        extends FromClientProto, ToServerProto, FromServerProto, ToDeviceProto {
    ClientServerDeviceCallType callType();
}
