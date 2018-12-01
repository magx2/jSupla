package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelValueEncoderImpl implements SuplaChannelValueEncoder {
    public static final SuplaChannelValueEncoderImpl INSTANCE =
            new SuplaChannelValueEncoderImpl(PrimitiveEncoderImpl.INSTANCE,
                    pl.grzeslowski.jsupla.protocol.impl.encoders.SuplaChannelValueEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;
    private final pl.grzeslowski.jsupla.protocol.api.encoders.SuplaChannelValueEncoder channelValueEncoder;

    SuplaChannelValueEncoderImpl(PrimitiveEncoder primitiveEncoder,
                                 pl.grzeslowski.jsupla.protocol.api.encoders.SuplaChannelValueEncoder
                                                channelValueEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
        this.channelValueEncoder = requireNonNull(channelValueEncoder);
    }

    @Override
    public byte[] encode(SuplaChannelValue proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeByte(proto.eol, data, offset);
        offset += primitiveEncoder.writeInteger(proto.id, data, offset);
        offset += primitiveEncoder.writeByte(proto.online, data, offset);
        final byte[] channelBytes = channelValueEncoder.encode(proto.value);
        primitiveEncoder.writeBytes(channelBytes, data, offset);

        return data;
    }
}
