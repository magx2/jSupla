package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelGroupDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroup;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public class SuplaChannelGroupDecoderImpl implements SuplaChannelGroupDecoder {
    public static final SuplaChannelGroupDecoderImpl INSTANCE = new SuplaChannelGroupDecoderImpl();

    @SuppressWarnings("UnusedAssignment")
    @Override
    public SuplaChannelGroup decode(final byte[] bytes, int offset) {
        final byte eol = PrimitiveDecoderImpl.INSTANCE.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final int id = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int locationId = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int func = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int altIcon = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final long flags = PrimitiveDecoderImpl.INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final long captionSize = PrimitiveDecoderImpl.INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] caption = PrimitiveDecoderImpl.INSTANCE.copyOfRange(bytes, offset, (int) (offset + captionSize));
        offset += caption.length;

        return new SuplaChannelGroup(
            eol,
            id,
            locationId,
            func,
            altIcon,
            flags,
            captionSize,
            caption
        );
    }
}
