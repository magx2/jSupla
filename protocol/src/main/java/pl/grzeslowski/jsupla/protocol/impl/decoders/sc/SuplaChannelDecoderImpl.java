package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.sc.ServerClientDecoder;
import pl.grzeslowski.jsupla.protocol.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaChannel;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNEL_CAPTION_MAXSIZE;

public final class SuplaChannelDecoderImpl implements ServerClientDecoder<SuplaChannel> {
    private final PrimitiveDecoder primitiveDecoder;
    private final SuplaChannelValueDecoder suplaChannelValueDecoder;

    public SuplaChannelDecoderImpl(PrimitiveDecoder primitiveDecoder, SuplaChannelValueDecoder suplaChannelValueDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
        this.suplaChannelValueDecoder = requireNonNull(suplaChannelValueDecoder);
    }

    @Override
    public SuplaChannel decode(byte[] bytes, int offset) {
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

        final byte[] caption = Arrays.copyOfRange(bytes, offset, offset + SUPLA_CHANNEL_CAPTION_MAXSIZE);

        return new SuplaChannel(eol, id, locationId, func, online, value, captionSize, caption);
    }

}
