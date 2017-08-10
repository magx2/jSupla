package pl.grzeslowski.jsupla.protocol.encoders.dcs;

import pl.grzeslowski.jsupla.protocol.encoders.ToSuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.structs.dcs.DeviceClientServer;

public interface DeviceClientServerEncoder<T extends DeviceClientServer> extends ToSuplaDataPacketEncoder<T> {
}
