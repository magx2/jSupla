package pl.grzeslowski.jsupla.protocol.api.decoders.sdc;

import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.ServerDeviceClient;

public interface ServerClientDeviceDecoder<T extends ServerDeviceClient> extends ProtoWithSizeDecoder<T> {
}
