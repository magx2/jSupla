package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.sc.ServerClientEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaLocation;

import static java.util.Objects.requireNonNull;

public final class SuplaLocationEncoderImpl implements ServerClientEncoder<SuplaLocation> {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaLocationEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaLocation proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeByte(proto.eol, data, offset);
        offset += primitiveEncoder.writeInteger(proto.id, data, offset);
        offset += primitiveEncoder.writeUnsignedInteger(proto.captionSize, data, offset);
        offset += primitiveEncoder.writeBytes(proto.caption, data, offset);

        return data;
    }
}
