package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.ProtoWithSize;

public interface EncoderFactory {
    <T extends ProtoWithSize> Encoder<T> getEncoderForServerDevice(T t);
}
