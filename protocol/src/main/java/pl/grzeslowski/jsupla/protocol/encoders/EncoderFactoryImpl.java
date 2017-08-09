package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.encoders.sd.TSD_SuplaRegisterDeviceResultEncoder;
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
    public <T extends Proto> Encoder<T> getEncoderForServerDevice(T t) {
        if (t instanceof SuplaRegisterDeviceResult) {
            return (Encoder<T>) new TSD_SuplaRegisterDeviceResultEncoder(version, idGenerator);
        }

        throw new IllegalArgumentException(format("Don't know encoder for class %s!", t.getClass().getSimpleName()));
    }
}
