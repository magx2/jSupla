package pl.grzeslowski.jsupla.proto.encoders;

import pl.grzeslowski.jsupla.proto.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.server.DataPacketIdGenerator;
import pl.grzeslowski.jsupla.server.entities.responses.RegisterDeviceResponse;

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
        if (sd instanceof RegisterDeviceResponse) {
            return (Encoder<SD>) new TSD_SuplaRegisterDeviceResultEncoder(version, idGenerator);
        }

        throw new IllegalArgumentException(format("Don't know encoder for class %s!", sd.getClass().getSimpleName()));
    }
}
