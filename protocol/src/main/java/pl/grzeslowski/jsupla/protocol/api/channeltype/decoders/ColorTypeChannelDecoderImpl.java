package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

class ColorTypeChannelDecoderImpl implements Decoder<RgbValue> {
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
