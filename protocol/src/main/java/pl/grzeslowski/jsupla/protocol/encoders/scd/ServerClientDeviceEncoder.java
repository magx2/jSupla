package pl.grzeslowski.jsupla.protocol.encoders.scd;

import pl.grzeslowski.jsupla.protocol.encoders.ToSuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.structs.scd.ServerClientDevice;

public interface ServerClientDeviceEncoder<T extends ServerClientDevice> extends ToSuplaDataPacketEncoder<T> {
}
