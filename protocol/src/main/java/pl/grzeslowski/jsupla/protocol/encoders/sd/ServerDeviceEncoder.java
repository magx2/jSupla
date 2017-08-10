package pl.grzeslowski.jsupla.protocol.encoders.sd;

import pl.grzeslowski.jsupla.protocol.encoders.SuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.ServerDevice;

public interface ServerDeviceEncoder<T extends ServerDevice> extends SuplaDataPacketEncoder<T> {
}
