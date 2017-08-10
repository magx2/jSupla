package pl.grzeslowski.jsupla.protocol.encoders.sd;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.FirmwareUpdateUrlResult;

import static java.util.Objects.requireNonNull;

public final class FirmwareUpdateUrlResultEncoder implements ServerDeviceEncoder<FirmwareUpdateUrlResult> {
    private final FirmwareUpdateUrlEncoder firmwareUpdateUrlEncoder;

    public FirmwareUpdateUrlResultEncoder(FirmwareUpdateUrlEncoder firmwareUpdateUrlEncoder) {
        this.firmwareUpdateUrlEncoder = requireNonNull(firmwareUpdateUrlEncoder);
    }

    @Override
    public byte[] encode(FirmwareUpdateUrlResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeByte(proto.exists, data, offset);
        final byte[] url = firmwareUpdateUrlEncoder.encode(proto.url);
        PrimitiveEncoder.writeBytes(url, data, offset);

        return data;
    }
}
