package pl.grzeslowski.jsupla.protocol.impl.encoders;

import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

public class EncoderFactoryImpl implements EncoderFactory {
    @Override
    public <T extends ProtoWithSize> Encoder<T> getEncoder(final T proto) {
        throw new UnsupportedOperationException("EncoderFactoryImpl.getEncoder(proto)");
    }
}
