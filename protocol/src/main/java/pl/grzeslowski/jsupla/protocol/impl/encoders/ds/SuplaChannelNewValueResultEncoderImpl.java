package pl.grzeslowski.jsupla.protocol.impl.encoders.ds;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.ds.DeviceServerEncoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaChannelNewValueResult;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelNewValueResultEncoderImpl implements DeviceServerEncoder<SuplaChannelNewValueResult> {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaChannelNewValueResultEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaChannelNewValueResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeUnsignedByte(proto.channelNumber, data, offset);
        offset += primitiveEncoder.writeInteger(proto.senderId, data, offset);
        offset += primitiveEncoder.writeByte(proto.success, data, offset);

        return data;
    }
}
