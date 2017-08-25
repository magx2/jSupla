package pl.grzeslowski.jsupla.protocol.api.decoders.sd;

import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.ServerDevice;

public interface ServerDeviceDecoder<T extends ServerDevice> extends ProtoWithSizeDecoder<T> {
}
