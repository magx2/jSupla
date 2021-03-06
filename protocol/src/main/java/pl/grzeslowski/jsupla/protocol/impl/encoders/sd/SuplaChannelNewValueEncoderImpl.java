package pl.grzeslowski.jsupla.protocol.impl.encoders.sd;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sd.SuplaChannelNewValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelNewValueEncoderImpl implements SuplaChannelNewValueEncoder {
    public static final SuplaChannelNewValueEncoderImpl INSTANCE =
            new SuplaChannelNewValueEncoderImpl(PrimitiveEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;

    SuplaChannelNewValueEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaChannelNewValue proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.senderId, data, offset);
        offset += primitiveEncoder.writeUnsignedByte(proto.channelNumber, data, offset);
        offset += primitiveEncoder.writeUnsignedInteger(proto.durationMs, data, offset);
        offset += primitiveEncoder.writeBytes(proto.value, data, offset);

        return data;
    }
}
