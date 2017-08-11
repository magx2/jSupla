package pl.grzeslowski.jsupla.server.dispatchers;

import pl.grzeslowski.jsupla.protocol.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.types.ProtoWithSize;

public interface EncoderFactory {
    <T extends ProtoWithSize> Encoder<T> getEncoderForServerDevice(T t);
}
