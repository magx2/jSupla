package pl.grzeslowski.jsupla.protocol.encoders.sdc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sdc.SuplaVersionError;

import static java.util.Objects.requireNonNull;

public final class SuplaVersionErrorEncoder implements ServerClientDeviceEncoder<SuplaVersionError> {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaVersionErrorEncoder(PrimitiveEncoder primitiveEncoder) {
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
