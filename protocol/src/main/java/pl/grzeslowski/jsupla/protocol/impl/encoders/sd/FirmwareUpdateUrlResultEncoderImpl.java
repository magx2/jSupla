package pl.grzeslowski.jsupla.protocol.impl.encoders.sd;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.sd.ServerDeviceEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.FirmwareUpdateUrlResult;

import static java.util.Objects.requireNonNull;

public final class FirmwareUpdateUrlResultEncoderImpl implements ServerDeviceEncoder<FirmwareUpdateUrlResult> {
    private final PrimitiveEncoder primitiveEncoder;
    private final FirmwareUpdateUrlEncoderImpl firmwareUpdateUrlEncoder;

    public FirmwareUpdateUrlResultEncoderImpl(PrimitiveEncoder primitiveEncoder,
                                              FirmwareUpdateUrlEncoderImpl firmwareUpdateUrlEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
        this.firmwareUpdateUrlEncoder = requireNonNull(firmwareUpdateUrlEncoder);
    }

    @Override
    public byte[] encode(FirmwareUpdateUrlResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeByte(proto.exists, data, offset);
        final byte[] url = firmwareUpdateUrlEncoder.encode(proto.url);
        primitiveEncoder.writeBytes(url, data, offset);

        return data;
    }
}
