package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.ProtoWithSize;
import pl.grzeslowski.jsupla.protocol.encoders.sd.SuplaRegisterDeviceResultEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.SuplaRegisterDeviceResult;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class EncoderFactoryImpl implements EncoderFactory {
    private final int version;
    private final DataPacketIdGenerator idGenerator;

    public EncoderFactoryImpl(int version, DataPacketIdGenerator idGenerator) {
        this.version = version;
        this.idGenerator = requireNonNull(idGenerator);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends ProtoWithSize> Encoder<T> getEncoderForServerDevice(T t) {
        if (t instanceof SuplaRegisterDeviceResult) {
            return (Encoder<T>) new SuplaRegisterDeviceResultEncoder(version, idGenerator);
        }

        throw new IllegalArgumentException(format("Don't know encoder for class %s!", t.getClass().getSimpleName()));
    }
}
