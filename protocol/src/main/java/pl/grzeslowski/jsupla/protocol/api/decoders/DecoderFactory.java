package pl.grzeslowski.jsupla.protocol.api.decoders;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public interface DecoderFactory {
    <T extends ProtoWithSize> Decoder<T> getDecoder(T proto);
}
