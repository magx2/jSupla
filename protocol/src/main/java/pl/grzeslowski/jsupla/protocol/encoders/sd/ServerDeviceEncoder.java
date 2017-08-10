package pl.grzeslowski.jsupla.protocol.encoders.sd;

import pl.grzeslowski.jsupla.protocol.encoders.ToSuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.ServerDevice;

public interface ServerDeviceEncoder<T extends ServerDevice> extends ToSuplaDataPacketEncoder<T> {
}
