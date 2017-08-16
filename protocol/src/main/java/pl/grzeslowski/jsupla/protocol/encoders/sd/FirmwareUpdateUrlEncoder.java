package pl.grzeslowski.jsupla.protocol.encoders.sd;

import pl.grzeslowski.jsupla.protocol.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sd.FirmwareUpdateUrl;

import static java.util.Objects.requireNonNull;

public final class FirmwareUpdateUrlEncoder implements Encoder<FirmwareUpdateUrl> {
    private final PrimitiveEncoder primitiveEncoder;

    public FirmwareUpdateUrlEncoder(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(FirmwareUpdateUrl proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeByte(proto.availableProtocols, data, offset);
        offset += primitiveEncoder.writeBytes(proto.host, data, offset);
        offset += primitiveEncoder.writeInteger(proto.port, data, offset);
        offset += primitiveEncoder.writeBytes(proto.path, data, offset);

        return data;
    }
}
