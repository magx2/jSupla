package pl.grzeslowski.jsupla.protocol.impl.encoders.ds;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.ds.SuplaDeviceChannelEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;

import static java.util.Objects.requireNonNull;

@Deprecated
public final class SuplaDeviceChannelEncoderImpl implements SuplaDeviceChannelEncoder {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaDeviceChannelEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaDeviceChannel proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeUnsignedByte(proto.number, data, offset);
        offset += primitiveEncoder.writeInteger(proto.type, data, offset);
        offset += primitiveEncoder.writeBytes(proto.value, data, offset);

        return data;
    }
}
