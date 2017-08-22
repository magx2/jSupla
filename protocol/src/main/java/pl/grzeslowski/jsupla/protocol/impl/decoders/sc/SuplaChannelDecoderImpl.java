package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaChannelDecoderImpl implements SuplaChannelDecoder {
    private final PrimitiveDecoder primitiveDecoder;
    private final SuplaChannelValueDecoder suplaChannelValueDecoder;

    public SuplaChannelDecoderImpl(PrimitiveDecoder primitiveDecoder,
                                   SuplaChannelValueDecoder suplaChannelValueDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
        this.suplaChannelValueDecoder = requireNonNull(suplaChannelValueDecoder);
    }

    @Override
    public SuplaChannel decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaChannel.MIN_SIZE);

        final byte eol = primitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final int id = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int locationId = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int func = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte online = primitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final SuplaChannelValue value = suplaChannelValueDecoder.decode(bytes, offset);
        offset += value.size();

        final long captionSize = primitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] caption = primitiveDecoder.copyOfRange(bytes, offset, offset + (int) captionSize);

        return new SuplaChannel(eol, id, locationId, func, online, value, captionSize, caption);
    }

}
