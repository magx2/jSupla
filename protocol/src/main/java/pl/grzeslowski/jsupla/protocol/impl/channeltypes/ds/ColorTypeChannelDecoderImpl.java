package pl.grzeslowski.jsupla.protocol.impl.channeltypes.ds;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.channeltypes.ds.ColorTypeChannelDecoder;
import pl.grzeslowski.jsupla.protocol.api.channelvalues.RgbValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;

public class ColorTypeChannelDecoderImpl implements ColorTypeChannelDecoder {
    private static final int BYTES_MIN = 5;
    private final PrimitiveDecoder primitiveDecoder;

    public ColorTypeChannelDecoderImpl(final PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public RgbValue decode(final byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, BYTES_MIN + offset);

        final short brightness = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short colorBrightness = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short red = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short green = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short blue = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        return new RgbValue(brightness, colorBrightness, red, green, blue);
    }
}
