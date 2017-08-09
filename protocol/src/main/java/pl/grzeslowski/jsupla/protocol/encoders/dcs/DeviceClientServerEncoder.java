package pl.grzeslowski.jsupla.protocol.encoders.dcs;

import pl.grzeslowski.jsupla.protocol.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.structs.dcs.DeviceClientServer;

public interface DeviceClientServerEncoder<T extends DeviceClientServer> extends Encoder<T> {
}
