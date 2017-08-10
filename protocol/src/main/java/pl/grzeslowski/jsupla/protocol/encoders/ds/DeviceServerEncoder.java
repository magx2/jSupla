package pl.grzeslowski.jsupla.protocol.encoders.ds;

import pl.grzeslowski.jsupla.protocol.encoders.SuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.DeviceServer;

public interface DeviceServerEncoder<T extends DeviceServer> extends SuplaDataPacketEncoder<T> {
}
