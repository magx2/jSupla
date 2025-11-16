package pl.grzeslowski.jsupla.protocol.api.decoders.csd;

import pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.csd.ClientServerDevice;

public interface ClientServerDeviceDecoder<T extends ClientServerDevice>
        extends ProtoWithSizeDecoder<T> {}
