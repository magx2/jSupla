package pl.grzeslowski.jsupla.protocol.api.structs.sds;

import pl.grzeslowski.jsupla.protocol.api.types.FromDeviceProto;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToDeviceProto;
import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;

public interface ServerDeviceServer extends FromServerProto, ToDeviceProto, FromDeviceProto, ToServerProto {
}
