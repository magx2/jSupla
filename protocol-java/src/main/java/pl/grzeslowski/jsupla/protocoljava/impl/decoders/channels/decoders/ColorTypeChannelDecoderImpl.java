package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.decoders;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;
import pl.grzeslowski.jsupla.protocoljava.api.channels.decoders.ColorTypeChannelDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.RgbValue;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;

public class ColorTypeChannelDecoderImpl implements ColorTypeChannelDecoder {
    public static final ColorTypeChannelDecoderImpl INSTANCE = new ColorTypeChannelDecoderImpl();
    private static final int BYTES_MIN = 5;

    
    @SuppressWarnings("WeakerAccess")
    ColorTypeChannelDecoderImpl() {
    }

    @SuppressWarnings({"UnusedAssignment", "Duplicates"})
    @Override
    public RgbValue decode(final byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, BYTES_MIN + offset);

        final short brightness = PrimitiveDecoderImpl.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short colorBrightness = PrimitiveDecoderImpl.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short red = PrimitiveDecoderImpl.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short green = PrimitiveDecoderImpl.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short blue = PrimitiveDecoderImpl.INSTANCE.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        return new RgbValue(brightness, colorBrightness, red, green, blue);
    }
}
