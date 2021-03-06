package pl.grzeslowski.jsupla.protocol.impl.encoders.sd;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sd.FirmwareUpdateUrlEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class FirmwareUpdateUrlEncoderImpl implements FirmwareUpdateUrlEncoder {
    public static final FirmwareUpdateUrlEncoderImpl INSTANCE =
            new FirmwareUpdateUrlEncoderImpl(PrimitiveEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;

    FirmwareUpdateUrlEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaFirmwareUpdateUrl proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeByte(proto.availableProtocols, data, offset);
        offset += primitiveEncoder.writeBytes(proto.host, data, offset);
        offset += primitiveEncoder.writeInteger(proto.port, data, offset);
        offset += primitiveEncoder.writeBytes(proto.path, data, offset);

        return data;
    }
}
