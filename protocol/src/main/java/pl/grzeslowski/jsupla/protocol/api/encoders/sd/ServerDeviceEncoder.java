package pl.grzeslowski.jsupla.protocol.api.encoders.sd;

import pl.grzeslowski.jsupla.protocol.api.encoders.ToSuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.ServerDevice;

public interface ServerDeviceEncoder<T extends ServerDevice> extends ToSuplaDataPacketEncoder<T> {
}
