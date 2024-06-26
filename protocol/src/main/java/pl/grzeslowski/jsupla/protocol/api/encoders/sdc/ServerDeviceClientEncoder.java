package pl.grzeslowski.jsupla.protocol.api.encoders.sdc;

import pl.grzeslowski.jsupla.protocol.api.encoders.ToSuplaDataPacketEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.ServerDeviceClient;

public interface ServerDeviceClientEncoder<T extends ServerDeviceClient> extends ToSuplaDataPacketEncoder<T> {
}
