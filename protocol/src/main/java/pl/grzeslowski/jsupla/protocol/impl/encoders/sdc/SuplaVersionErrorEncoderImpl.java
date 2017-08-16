package pl.grzeslowski.jsupla.protocol.impl.encoders.sdc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sdc.SuplaVersionErrorEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;

import static java.util.Objects.requireNonNull;

public final class SuplaVersionErrorEncoderImpl implements SuplaVersionErrorEncoder {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaVersionErrorEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @Override
    public byte[] encode(SuplaVersionError proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeUnsignedByte(proto.serverVersionMin, data, offset);
        offset += primitiveEncoder.writeUnsignedByte(proto.serverVersion, data, offset);

        return data;
    }
}
