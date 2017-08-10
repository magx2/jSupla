package pl.grzeslowski.jsupla.protocol.encoders.ds;

import pl.grzeslowski.jsupla.protocol.encoders.ToSuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.DeviceServer;

public interface DeviceServerEncoder<T extends DeviceServer> extends ToSuplaDataPacketEncoder<T> {
}
