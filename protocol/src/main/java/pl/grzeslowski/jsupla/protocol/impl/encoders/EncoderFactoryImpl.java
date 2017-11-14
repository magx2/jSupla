package pl.grzeslowski.jsupla.protocol.impl.encoders;

import pl.grzeslowski.jsupla.protocol.api.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.EncoderFactory;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sd.SuplaRegisterDeviceResultEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sdc.SuplaSetActivityTimeoutResultEncoderImpl;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;

// TODO implement this
public class EncoderFactoryImpl implements EncoderFactory {
    private final PrimitiveEncoder primitiveEncoder = INSTANCE;

    public EncoderFactoryImpl(final PrimitiveEncoder primitiveEncoder) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ProtoWithSize> Encoder<T> getEncoder(final T proto) {
        if (proto instanceof SuplaRegisterDeviceResult) {
            return (Encoder<T>) new SuplaRegisterDeviceResultEncoderImpl(primitiveEncoder);
        } else if (proto instanceof SuplaSetActivityTimeoutResult) {
            return (Encoder<T>) new SuplaSetActivityTimeoutResultEncoderImpl(primitiveEncoder);
        }

        throw new IllegalArgumentException(format("do not know %s", proto.getClass().getSimpleName()));
    }
}
