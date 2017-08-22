package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelPackDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaChannelPackDecoderImpl implements SuplaChannelPackDecoder {
    private final PrimitiveDecoder primitiveDecoder;
    private final SuplaChannelDecoder channelDecoder;

    public SuplaChannelPackDecoderImpl(PrimitiveDecoder primitiveDecoder, SuplaChannelDecoder channelDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
        this.channelDecoder = requireNonNull(channelDecoder);
    }

    @Override
    public SuplaChannelPack decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaChannelPack.MIN_SIZE);

        final int count = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int totalLeft = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final SuplaChannel[] channels = new SuplaChannel[count];
        for (int i = 0; i < count; i++) {
            channels[i] = channelDecoder.decode(bytes, offset);
            offset += channels[i].size();
        }

        return new SuplaChannelPack(count, totalLeft, channels);
    }
}
