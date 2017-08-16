package pl.grzeslowski.jsupla.protocol.impl.encoders.sdc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.sdc.ServerClientDeviceEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sdc.SuplaVersionError;

import static java.util.Objects.requireNonNull;

public final class SuplaVersionErrorEncoderImpl implements ServerClientDeviceEncoder<SuplaVersionError> {
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
