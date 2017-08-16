package pl.grzeslowski.jsupla.server.dispatchers;

import pl.grzeslowski.jsupla.protocol.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sd.SuplaRegisterDeviceResultEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.sdc.SuplaSetActivityTimeoutResultEncoderImpl;
import pl.grzeslowski.jsupla.protocol.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.structs.sdc.SuplaSetActivityTimeoutResult;
import pl.grzeslowski.jsupla.protocol.types.ProtoWithSize;

import static java.lang.String.format;

public class EncoderFactoryImpl implements EncoderFactory {
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ProtoWithSize> Encoder<T> getEncoderForServerDevice(T t) {
        if (t instanceof SuplaRegisterDeviceResult) {
            return (Encoder<T>) new SuplaRegisterDeviceResultEncoderImpl(PrimitiveEncoderImpl.INSTANCE);
        } else if (t instanceof SuplaSetActivityTimeoutResult) {
            return (Encoder<T>) new SuplaSetActivityTimeoutResultEncoderImpl(PrimitiveEncoderImpl.INSTANCE);
        }

        throw new IllegalArgumentException(format("Don't know encoder for class %s!", t.getClass().getSimpleName()));
    }
}
