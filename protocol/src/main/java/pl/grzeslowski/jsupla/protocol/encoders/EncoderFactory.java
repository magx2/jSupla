package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.PackableProto;

public interface EncoderFactory {
    <T extends PackableProto> Encoder<T> getEncoderForServerDevice(T t);
}
