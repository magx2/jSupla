package pl.grzeslowski.jsupla.protocol.decoders.sc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaChannel;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNEL_CAPTION_MAXSIZE;

public final class SuplaChannelDecoder implements ServerClientDecoder<SuplaChannel> {
    private final SuplaChannelValueDecoder suplaChannelValueDecoder;

    public SuplaChannelDecoder(SuplaChannelValueDecoder suplaChannelValueDecoder) {
        this.suplaChannelValueDecoder = requireNonNull(suplaChannelValueDecoder);
    }

    @Override
    public SuplaChannel decode(byte[] bytes, int offset) {
        final byte eol = PrimitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final int id = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int locationId = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int func = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte online = PrimitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final SuplaChannelValue value = suplaChannelValueDecoder.decode(bytes, offset);
        offset += value.size();

        final long captionSize = PrimitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] caption = Arrays.copyOfRange(bytes, offset, offset + SUPLA_CHANNEL_CAPTION_MAXSIZE);

        return new SuplaChannel(eol, id, locationId, func, online, value, captionSize, caption);
    }

}
