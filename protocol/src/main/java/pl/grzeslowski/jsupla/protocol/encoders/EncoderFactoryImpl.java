package pl.grzeslowski.jsupla.protocol.encoders;

import pl.grzeslowski.jsupla.protocol.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.protocol.structs.sd.TSD_SuplaRegisterDeviceResult;

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
    public <SD extends ServerDevice> Encoder<SD> getEncoderForServerDevice(SD sd) {
        if (sd instanceof TSD_SuplaRegisterDeviceResult) {
            return (Encoder<SD>) new TSD_SuplaRegisterDeviceResultEncoder(version, idGenerator);
        }

        throw new IllegalArgumentException(format("Don't know encoder for class %s!", sd.getClass().getSimpleName()));
    }
}
