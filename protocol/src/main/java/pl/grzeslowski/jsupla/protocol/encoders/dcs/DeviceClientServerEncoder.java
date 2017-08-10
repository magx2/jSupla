package pl.grzeslowski.jsupla.protocol.encoders.dcs;

import pl.grzeslowski.jsupla.protocol.encoders.SuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.structs.dcs.DeviceClientServer;

public interface DeviceClientServerEncoder<T extends DeviceClientServer> extends SuplaDataPacketEncoder<T> {
}
