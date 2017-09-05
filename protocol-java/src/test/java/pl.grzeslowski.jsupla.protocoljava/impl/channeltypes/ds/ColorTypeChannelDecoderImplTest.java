package pl.grzeslowski.jsupla.protocoljava.impl.channeltypes.ds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.RgbValue;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channeltypes.ds.ColorTypeChannelDecoderImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;

@SuppressWarnings("WeakerAccess")
@RunWith(MockitoJUnitRunner.class)
public class ColorTypeChannelDecoderImplTest {
    static final int BYTES_SIZE = 5;
    @InjectMocks ColorTypeChannelDecoderImpl decoder;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenArrayIsTooSmall() throws Exception {

        // given
        final int offset = 3;
        final byte[] bytes = new byte[BYTES_SIZE + offset - 1];

        // when
        decoder.decode(bytes, offset);
    }

    @SuppressWarnings("UnusedAssignment")
    @Test
    public void shouldShouldParseRgbValue() throws Exception {

        // given
        final short brightness = 1;
        final short colorBrightness = 2;
        final short red = 3;
        final short green = 4;
        final short blue = 5;

        int offset = 0;
        final byte[] bytes = new byte[BYTES_SIZE];
        offset += INSTANCE.writeUnsignedByte(brightness, bytes, offset);
        offset += INSTANCE.writeUnsignedByte(colorBrightness, bytes, offset);
        offset += INSTANCE.writeUnsignedByte(red, bytes, offset);
        offset += INSTANCE.writeUnsignedByte(green, bytes, offset);
        offset += INSTANCE.writeUnsignedByte(blue, bytes, offset);

        // when
        final RgbValue decode = decoder.decode(bytes);

        // then
        assertThat(decode.brightness).isEqualTo(brightness);
        assertThat(decode.colorBrightness).isEqualTo(colorBrightness);
        assertThat(decode.red).isEqualTo(red);
        assertThat(decode.green).isEqualTo(green);
        assertThat(decode.blue).isEqualTo(blue);
    }

    @SuppressWarnings({"UnusedAssignment", "PointlessArithmeticExpression"})
    @Test
    public void shouldShouldParseRgbValueWithOffset() throws Exception {

        // given
        final short brightness = 1;
        final short colorBrightness = 2;
        final short red = 3;
        final short green = 4;
        final short blue = 5;

        final int initialOffset = 3;
        int offset = initialOffset;
        final byte[] bytes = new byte[BYTES_SIZE + initialOffset];
        offset += INSTANCE.writeUnsignedByte(brightness, bytes, offset);
        offset += INSTANCE.writeUnsignedByte(colorBrightness, bytes, offset);
        offset += INSTANCE.writeUnsignedByte(red, bytes, offset);
        offset += INSTANCE.writeUnsignedByte(green, bytes, offset);
        offset += INSTANCE.writeUnsignedByte(blue, bytes, offset);

        // when
        final RgbValue decode = decoder.decode(bytes, initialOffset);

        // then
        assertThat(decode.brightness).isEqualTo(brightness);
        assertThat(decode.colorBrightness).isEqualTo(colorBrightness);
        assertThat(decode.red).isEqualTo(red);
        assertThat(decode.green).isEqualTo(green);
        assertThat(decode.blue).isEqualTo(blue);
    }
}