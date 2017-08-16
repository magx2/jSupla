package pl.grzeslowski.jsupla.protocol.api.encoders.ds;

import pl.grzeslowski.jsupla.protocol.api.encoders.ToSuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer;

public interface DeviceServerEncoder<T extends DeviceServer> extends ToSuplaDataPacketEncoder<T> {
}
