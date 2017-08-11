package pl.grzeslowski.jsupla.server.dispatchers;

import pl.grzeslowski.jsupla.protocol.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.encoders.Encoder;

public interface EncoderFactory {
    <T extends ProtoWithSize> Encoder<T> getEncoderForServerDevice(T t);
}
