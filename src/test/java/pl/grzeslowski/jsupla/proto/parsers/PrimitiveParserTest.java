package pl.grzeslowski.jsupla.proto.parsers;


import org.junit.Test;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;

public class PrimitiveParserTest {

    @Test
    public void shouldParseIntFromOneByte() {

        // given
        byte[] bytes = new byte[]{(byte) 5, 0, 0, 0};

        // when
        final int parseInt = PrimitiveParser.parseInt(bytes, 0);

        // then
        assertThat(parseInt).isEqualTo(MIN_VALUE + 5);
    }

    @Test
    public void shouldParseIntFromTwoByte() {

        // given
        byte[] bytes = new byte[]{(byte) 5, 0, 0, 21};

        // when
        final int parseInt = PrimitiveParser.parseInt(bytes, 0);

        // then
        assertThat(parseInt).isEqualTo(MIN_VALUE + 352_321_541);
    }

    @Test
    public void shouldParseMaxIntFromFullInt() {

        // given
        byte[] bytes = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1};

        // when
        final int parseInt = PrimitiveParser.parseInt(bytes, 0);

        // then
        assertThat(parseInt).isEqualTo(MAX_VALUE);
    }

    @Test
    public void shouldParseMinIntFromFullInt() {

        // given
        byte[] bytes = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};

        // when
        final int parseInt = PrimitiveParser.parseInt(bytes, 0);

        // then
        assertThat(parseInt).isEqualTo(MIN_VALUE);
    }
}
