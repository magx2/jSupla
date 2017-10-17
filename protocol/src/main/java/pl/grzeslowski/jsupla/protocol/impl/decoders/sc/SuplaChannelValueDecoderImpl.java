package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

public final class SuplaChannelValueDecoderImpl implements SuplaChannelValueDecoder {
    private final pl.grzeslowski.jsupla.protocol.api.decoders.SuplaChannelValueDecoder channelValueDecoder;

    public SuplaChannelValueDecoderImpl(pl.grzeslowski.jsupla.protocol.api.decoders.SuplaChannelValueDecoder
                                            channelValueDecoder) {
        this.channelValueDecoder = requireNonNull(channelValueDecoder);
    }

    @Override
    public SuplaChannelValue decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaChannelValue.SIZE);

        final byte eol = INSTANCE.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final int id = INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte online = INSTANCE.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue value =
                channelValueDecoder.decode(bytes, offset);

        return new SuplaChannelValue(eol, id, online, value);
    }
}
