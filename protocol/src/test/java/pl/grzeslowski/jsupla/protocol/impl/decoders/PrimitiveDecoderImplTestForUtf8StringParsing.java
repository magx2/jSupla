package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

public class PrimitiveDecoderImplTestForUtf8StringParsing {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenArrayIsToShort() throws Exception {

        // given
        final int length = 5;
        final byte[] bytes = new byte[]{1, 2, 3, 4};

        // when
        INSTANCE.parseUtf8String(bytes, 0, length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenArrayIsToShortWithOffset() throws Exception {

        // given
        final int length = 5;
        final int offset = 3;
        final byte[] bytes = new byte[]{11, 12, 13, 1, 2, 3, 4};

        // when
        INSTANCE.parseUtf8String(bytes, offset, length);
    }

    @Test
    public void shouldShouldParseUtf8String() throws Exception {

        // given
        final String expectedString = "# € Œ ϻ ¥";
        final byte[] bytes = expectedString.getBytes();

        // when
        final String string = INSTANCE.parseUtf8String(bytes, 0, bytes.length);

        // then
        assertThat(string).isEqualTo(expectedString);
    }

    @Test
    public void shouldShouldParseUtf8StringWithOffset() throws Exception {

        // given
        final int offset = 3;
        final String expectedString = "# € Œ ϻ ¥";
        final byte[] bytes = new byte[offset + expectedString.getBytes().length];
        for (int i = 0; i < offset; i++) {
            bytes[i] = (byte) 1;
        }
        for (int i = offset; i < bytes.length; i++) {
            bytes[i] = expectedString.getBytes()[i - offset];
        }

        // when
        final String string = INSTANCE.parseUtf8String(bytes, offset, expectedString.getBytes().length);

        // then
        assertThat(string).isEqualTo(expectedString);
    }

    @Test
    public void shouldCutZerosOnTheEnd() throws Exception {

        // given
        final String expectedString = "# € Œ ϻ ¥";
        final int numberOfZeros = 100;
        final byte[] bytes = new byte[expectedString.getBytes().length + numberOfZeros];
        for (int i = 0; i < expectedString.getBytes().length; i++) {
            bytes[i] = expectedString.getBytes()[i];
        }

        // when
        final String string = INSTANCE.parseUtf8String(bytes, 0, bytes.length);

        // then
        assertThat(string).isEqualTo(expectedString);
    }

    @Test
    public void shouldCutZerosOnTheEndWithOffset() throws Exception {

        // given
        final String expectedString = "# € Œ ϻ ¥";
        final int offset = 5;
        final int numberOfZeros = 100;
        final byte[] bytes = new byte[offset + expectedString.getBytes().length + numberOfZeros];
        for (int i = 0; i < offset; i++) {
            bytes[i] = (byte) 1;
        }
        for (int i = offset; i < expectedString.getBytes().length + offset; i++) {
            bytes[i] = expectedString.getBytes()[i - offset];
        }

        // when
        final String string = INSTANCE.parseUtf8String(bytes, offset,
                expectedString.getBytes().length + numberOfZeros);

        // then
        assertThat(string).isEqualTo(expectedString);
    }
}