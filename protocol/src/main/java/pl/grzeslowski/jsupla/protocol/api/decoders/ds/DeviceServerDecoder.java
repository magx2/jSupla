package pl.grzeslowski.jsupla.protocol.api.decoders.ds;

import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer;

public interface DeviceServerDecoder<T extends DeviceServer> extends ProtoWithSizeDecoder<T> {
}
