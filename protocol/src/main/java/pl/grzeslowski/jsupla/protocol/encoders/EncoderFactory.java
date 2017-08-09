package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.Proto;

public interface EncoderFactory {
    <T extends Proto> Encoder<T> getEncoderForServerDevice(T t);
}
