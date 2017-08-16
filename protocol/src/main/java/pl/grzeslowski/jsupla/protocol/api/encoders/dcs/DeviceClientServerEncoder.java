package pl.grzeslowski.jsupla.protocol.api.encoders.dcs;

import pl.grzeslowski.jsupla.protocol.api.encoders.ToSuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;

public interface DeviceClientServerEncoder<T extends DeviceClientServer> extends ToSuplaDataPacketEncoder<T> {
}
