package pl.grzeslowski.jsupla.protocol.decoders.sc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaChannelPack;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class SuplaChannelPackDecoder implements ServerClientDecoder<SuplaChannelPack> {
    private final SuplaChannelDecoder channelDecoder;

    public SuplaChannelPackDecoder(SuplaChannelDecoder channelDecoder) {
        this.channelDecoder = requireNonNull(channelDecoder);
    }

    @Override
    public SuplaChannelPack decode(byte[] bytes, int offset) {
        final int count = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int totalLeft = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final SuplaChannel[] channels = new SuplaChannel[count];
        for (int i = 0; i < count; i++) {
            channels[i] = channelDecoder.decode(bytes, offset);
            offset += channels[i].size();
        }

        return new SuplaChannelPack(count, totalLeft, channels);
    }
}
