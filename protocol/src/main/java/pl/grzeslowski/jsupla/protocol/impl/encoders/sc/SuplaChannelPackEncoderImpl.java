package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.sc.ServerClientEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaChannelPack;

import static java.util.Objects.requireNonNull;

public final class SuplaChannelPackEncoderImpl implements ServerClientEncoder<SuplaChannelPack> {
    private final PrimitiveEncoder primitiveEncoder;
    private final SuplaChannelEncoderImpl channelEncoder;

    public SuplaChannelPackEncoderImpl(PrimitiveEncoder primitiveEncoder, SuplaChannelEncoderImpl channelEncoder) {
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
