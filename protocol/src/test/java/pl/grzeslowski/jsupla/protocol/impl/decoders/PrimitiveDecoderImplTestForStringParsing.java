package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

public class PrimitiveDecoderImplTestForStringParsing {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenArrayIsToShort() throws Exception {

        // given
        final int length = 5;
        final byte[] bytes = new byte[]{1, 2, 3, 4};

        // when
        INSTANCE.parseString(bytes, 0, length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenArrayIsToShortWithOffset() throws Exception {

        // given
        final int length = 5;
        final int offset = 3;
        final byte[] bytes = new byte[]{11, 12, 13, 1, 2, 3, 4};

        // when
        INSTANCE.parseString(bytes, offset, length);
    }

    @Test
    public void shouldShouldParseString() throws Exception {

        // given
        final String expectedString = "Martin1";
        final byte[] bytes = new byte[]{
                ((byte) 'M'),
                ((byte) 'a'),
                ((byte) 'r'),
                ((byte) 't'),
                ((byte) 'i'),
                ((byte) 'n'),
                ((byte) '1'),
        };

        // when
        final String string = INSTANCE.parseString(bytes, 0, expectedString.length());

        // then
        assertThat(string).isEqualTo(expectedString);
    }

    @Test
    public void shouldShouldParseStringWithOffset() throws Exception {

        // given
        final int offset = 3;
        final String expectedString = "Martin1";
        final byte[] bytes = new byte[]{
                ((byte) '3'),
                ((byte) '2'),
                ((byte) '1'),
                ((byte) 'M'),
                ((byte) 'a'),
                ((byte) 'r'),
                ((byte) 't'),
                ((byte) 'i'),
                ((byte) 'n'),
                ((byte) '1'),
        };

        // when
        final String string = INSTANCE.parseString(bytes, offset, expectedString.length());

        // then
        assertThat(string).isEqualTo(expectedString);
    }

    @Test
    public void shouldCutZerosOnTheEnd() throws Exception {

        // given
        final String expectedString = "Martin1";
        final byte[] bytes = new byte[]{
                ((byte) 'M'),
                ((byte) 'a'),
                ((byte) 'r'),
                ((byte) 't'),
                ((byte) 'i'),
                ((byte) 'n'),
                ((byte) '1'),
                ((byte) 0),
                ((byte) 0),
                ((byte) 0),
                ((byte) 0),
                ((byte) 0),
                ((byte) 0),
                ((byte) 0),
                ((byte) 0),
        };

        // when
        final String string = INSTANCE.parseString(bytes, 0, expectedString.length());

        // then
        assertThat(string).isEqualTo(expectedString);
    }

    @Test
    public void shouldCutZerosOnTheEndWithOffset() throws Exception {

        // given
        final int offset = 3;
        final String expectedString = "Martin1";
        final byte[] bytes = new byte[]{
                ((byte) '4'),
                ((byte) '5'),
                ((byte) '6'),
                ((byte) 'M'),
                ((byte) 'a'),
                ((byte) 'r'),
                ((byte) 't'),
                ((byte) 'i'),
                ((byte) 'n'),
                ((byte) '1'),
                ((byte) 0),
                ((byte) 0),
                ((byte) 0),
                ((byte) 0),
                ((byte) 0),
                ((byte) 0),
                ((byte) 0),
                ((byte) 0),
        };

        // when
        final String string = INSTANCE.parseString(bytes, offset, expectedString.length());

        // then
        assertThat(string).isEqualTo(expectedString);
    }
}