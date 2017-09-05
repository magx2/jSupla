package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channeltypes.ds;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ColorTypeChannelDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.RgbValue;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

public class ColorTypeChannelDecoderImpl implements ColorTypeChannelDecoder {
    private static final int BYTES_MIN = 5;

    @SuppressWarnings("UnusedAssignment")
    @Override
    public RgbValue decode(final byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, BYTES_MIN + offset);

        final short brightness = INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short colorBrightness = INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short red = INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short green = INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short blue = INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        return new RgbValue(brightness, colorBrightness, red, green, blue);
    }
}
