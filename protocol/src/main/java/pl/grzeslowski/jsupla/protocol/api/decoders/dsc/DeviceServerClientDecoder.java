package pl.grzeslowski.jsupla.protocol.api.decoders.dsc;

import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.dsc.DeviceServerClient;

public interface DeviceServerClientDecoder<T extends DeviceServerClient> extends ProtoWithSizeDecoder<T> {
}
