package pl.grzeslowski.jsupla.protocol.decoders.sc;


import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaChannelValue;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class SuplaChannelValueDecoderImpl implements ServerClientDecoder<SuplaChannelValue> {
    private final PrimitiveDecoder primitiveDecoder;
    private final pl.grzeslowski.jsupla.protocol.decoders.SuplaChannelValueDecoder channelValueDecoder;

    public SuplaChannelValueDecoderImpl(PrimitiveDecoder primitiveDecoder,
                                        pl.grzeslowski.jsupla.protocol.decoders.SuplaChannelValueDecoder
                                            channelValueDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
        this.channelValueDecoder = requireNonNull(channelValueDecoder);
    }

    @Override
    public SuplaChannelValue decode(byte[] bytes, int offset) {
        final byte eol = primitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final int id = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte online = primitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final pl.grzeslowski.jsupla.protocol.structs.SuplaChannelValue value =
                channelValueDecoder.decode(bytes, offset);

        return new SuplaChannelValue(eol, id, online, value);
    }
}
