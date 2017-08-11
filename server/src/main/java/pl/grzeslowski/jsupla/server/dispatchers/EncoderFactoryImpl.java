package pl.grzeslowski.jsupla.server.dispatchers;

import pl.grzeslowski.jsupla.protocol.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.encoders.sd.SuplaRegisterDeviceResultEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.sdc.SuplaSetActivityTimeoutResultEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.structs.sdc.SuplaSetActivityTimeoutResult;

import static java.lang.String.format;

public class EncoderFactoryImpl implements EncoderFactory {
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ProtoWithSize> Encoder<T> getEncoderForServerDevice(T t) {
        if (t instanceof SuplaRegisterDeviceResult) {
            return (Encoder<T>) new SuplaRegisterDeviceResultEncoder();
        } else if (t instanceof SuplaSetActivityTimeoutResult) {
            return (Encoder<T>) new SuplaSetActivityTimeoutResultEncoder();
        }

        throw new IllegalArgumentException(format("Don't know encoder for class %s!", t.getClass().getSimpleName()));
    }
}
