package pl.grzeslowski.jsupla.protocol.encoders.sdc;

import pl.grzeslowski.jsupla.protocol.encoders.ToSuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sdc.ServerDeviceClient;

public interface ServerClientDeviceEncoder<T extends ServerDeviceClient> extends ToSuplaDataPacketEncoder<T> {
}
