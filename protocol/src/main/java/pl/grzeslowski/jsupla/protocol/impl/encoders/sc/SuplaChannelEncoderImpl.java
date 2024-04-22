package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.SuplaChannelValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.encoders.SuplaChannelValueEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelEncoderImpl implements SuplaChannelEncoder {
    public static final SuplaChannelEncoderImpl INSTANCE =
        new SuplaChannelEncoderImpl(PrimitiveEncoderImpl.INSTANCE, SuplaChannelValueEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;
    private final SuplaChannelValueEncoder channelValueEncoder;

    SuplaChannelEncoderImpl(PrimitiveEncoder primitiveEncoder,
                            SuplaChannelValueEncoder channelValueEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
        this.channelValueEncoder = requireNonNull(channelValueEncoder);
    }

    @Override
    public byte[] encode(SuplaChannel proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeByte(proto.eol, data, offset);
        offset += primitiveEncoder.writeInteger(proto.id, data, offset);
        offset += primitiveEncoder.writeInteger(proto.locationId, data, offset);
        offset += primitiveEncoder.writeInteger(proto.func, data, offset);
        offset += primitiveEncoder.writeInteger(proto.online, data, offset);
        offset += primitiveEncoder.writeUnsignedInteger(proto.captionSize, data, offset);
        final byte[] value = channelValueEncoder.encode(proto.value);
        primitiveEncoder.writeBytes(value, data, offset);

        return data;
    }
}
