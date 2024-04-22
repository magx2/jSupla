package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaChannelPackEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelPackEncoderImpl implements SuplaChannelPackEncoder {
    public static final SuplaChannelPackEncoderImpl INSTANCE =
        new SuplaChannelPackEncoderImpl(PrimitiveEncoderImpl.INSTANCE, SuplaChannelEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;
    private final SuplaChannelEncoder channelEncoder;

    SuplaChannelPackEncoderImpl(PrimitiveEncoder primitiveEncoder, SuplaChannelEncoder channelEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
        this.channelEncoder = requireNonNull(channelEncoder);
    }

    @Override
    public byte[] encode(SuplaChannelPack proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.count, data, offset);
        offset += primitiveEncoder.writeInteger(proto.totalLeft, data, offset);
        for (SuplaChannel channel : proto.channels) {
            final byte[] channelBytes = channelEncoder.encode(channel);
            offset += primitiveEncoder.writeBytes(channelBytes, data, offset);
        }

        return data;
    }
}
