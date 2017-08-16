package pl.grzeslowski.jsupla.protocol.encoders.ds;

import pl.grzeslowski.jsupla.protocol.encoders.Encoder;
import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaDeviceChannelB;

import static java.util.Objects.requireNonNull;

public final class SuplaDeviceChannelBEncoder implements Encoder<SuplaDeviceChannelB> {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaDeviceChannelBEncoder(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaDeviceChannelB proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeUnsignedByte(proto.number, data, offset);
        offset += primitiveEncoder.writeInteger(proto.type, data, offset);
        offset += primitiveEncoder.writeInteger(proto.funcList, data, offset);
        offset += primitiveEncoder.writeInteger(proto.defaultValue, data, offset);
        offset += primitiveEncoder.writeBytes(proto.value, data, offset);

        return data;
    }
}
