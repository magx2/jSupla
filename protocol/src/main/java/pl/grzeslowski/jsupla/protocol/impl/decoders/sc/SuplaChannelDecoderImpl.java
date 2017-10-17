package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

public final class SuplaChannelDecoderImpl implements SuplaChannelDecoder {
    private final SuplaChannelValueDecoder suplaChannelValueDecoder;

    public SuplaChannelDecoderImpl(SuplaChannelValueDecoder suplaChannelValueDecoder) {
        this.suplaChannelValueDecoder = requireNonNull(suplaChannelValueDecoder);
    }

    @Override
    public SuplaChannel decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaChannel.MIN_SIZE);

        final byte eol = INSTANCE.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final int id = INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int locationId = INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int func = INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte online = INSTANCE.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final SuplaChannelValue value = suplaChannelValueDecoder.decode(bytes, offset);
        offset += value.size();

        final long captionSize = INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] caption = INSTANCE.copyOfRange(bytes, offset, offset + (int) captionSize);

        return new SuplaChannel(eol, id, locationId, func, online, value, captionSize, caption);
    }

}
