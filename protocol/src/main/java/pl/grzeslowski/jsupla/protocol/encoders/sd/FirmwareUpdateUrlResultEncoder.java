package pl.grzeslowski.jsupla.protocol.encoders.sd;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.FirmwareUpdateUrlResult;

import static java.util.Objects.requireNonNull;

public final class FirmwareUpdateUrlResultEncoder implements ServerDeviceEncoder<FirmwareUpdateUrlResult> {
    private final PrimitiveEncoder primitiveEncoder;
    private final FirmwareUpdateUrlEncoder firmwareUpdateUrlEncoder;

    public FirmwareUpdateUrlResultEncoder(PrimitiveEncoder primitiveEncoder,
                                          FirmwareUpdateUrlEncoder firmwareUpdateUrlEncoder) {
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
