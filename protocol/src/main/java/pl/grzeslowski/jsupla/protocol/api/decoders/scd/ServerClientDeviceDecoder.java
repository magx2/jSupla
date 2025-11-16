package pl.grzeslowski.jsupla.protocol.api.decoders.scd;

import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.scd.ServerClientDevice;

public interface ServerClientDeviceDecoder<T extends ServerClientDevice>
        extends ProtoWithSizeDecoder<T> {}
