package pl.grzeslowski.jsupla.protocol.impl.encoders.sd;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sd.FirmwareUpdateUrlEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sd.FirmwareUpdateUrlResultEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class FirmwareUpdateUrlResultEncoderImpl implements FirmwareUpdateUrlResultEncoder {
    public static final FirmwareUpdateUrlResultEncoderImpl INSTANCE = new FirmwareUpdateUrlResultEncoderImpl(
            PrimitiveEncoderImpl.INSTANCE, FirmwareUpdateUrlEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;
    private final FirmwareUpdateUrlEncoder firmwareUpdateUrlEncoder;

    FirmwareUpdateUrlResultEncoderImpl(PrimitiveEncoder primitiveEncoder,
                                       FirmwareUpdateUrlEncoder firmwareUpdateUrlEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
        this.firmwareUpdateUrlEncoder = requireNonNull(firmwareUpdateUrlEncoder);
    }

    @Override
    public byte[] encode(SuplaFirmwareUpdateUrlResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeByte(proto.exists, data, offset);
        final byte[] url = firmwareUpdateUrlEncoder.encode(proto.url);
        primitiveEncoder.writeBytes(url, data, offset);

        return data;
    }
}
