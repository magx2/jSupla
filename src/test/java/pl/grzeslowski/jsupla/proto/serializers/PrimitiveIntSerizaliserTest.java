package pl.grzeslowski.jsupla.proto.serializers;

import org.junit.Test;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;

public class PrimitiveIntSerizaliserTest {
    private static final int VALUE_THAT_I_DO_NOT_CARE = 5;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenArrayIsToSmall() {

        // given
        byte[] bytes = new byte[INT_SIZE - 1];

        // when
        PrimitiveSerizaliser.putUnsignedInt(VALUE_THAT_I_DO_NOT_CARE, bytes, 0);

        // then
        fail("Should throw IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenArrayIsToSmallBecauseOfOffset() {

        // given
        int offset = 3;
        byte[] bytes = new byte[offset + INT_SIZE - 1];

        // when
        PrimitiveSerizaliser.putUnsignedInt(VALUE_THAT_I_DO_NOT_CARE, bytes, offset);

        // then
        fail("Should throw IllegalArgumentException");
    }

    @Test
    public void shouldPutSmallUnsignedIntIntoBuffer() {

        // given
        int value = 5 + MIN_VALUE; // 5(10) is 00000101(2)
        byte[] bytes = new byte[INT_SIZE];

        // when
        PrimitiveSerizaliser.putUnsignedInt(value, bytes, 0);

        // then
        assertThat(bytes[0]).isEqualTo((byte) 5);
        assertThat(bytes[1]).isEqualTo((byte) 0);
        assertThat(bytes[2]).isEqualTo((byte) 0);
        assertThat(bytes[3]).isEqualTo((byte) 0);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Test
    public void shouldPutMinimalUnsignedIntIntoBuffer() {

        // given
        int value = MIN_VALUE;
        byte[] bytes = new byte[INT_SIZE];

        // when
        PrimitiveSerizaliser.putUnsignedInt(value, bytes, 0);

        // then
        assertThat(bytes[0]).isEqualTo((byte) 0);
        assertThat(bytes[1]).isEqualTo((byte) 0);
        assertThat(bytes[2]).isEqualTo((byte) 0);
        assertThat(bytes[3]).isEqualTo((byte) 0);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Test
    public void shouldPutMaxUnsignedIntIntoBuffer() {

        // given
        int value = MAX_VALUE;
        byte[] bytes = new byte[INT_SIZE];

        // when
        PrimitiveSerizaliser.putUnsignedInt(value, bytes, 0);

        // then
        assertThat(bytes[0]).isEqualTo((byte) -1);
        assertThat(bytes[1]).isEqualTo((byte) -1);
        assertThat(bytes[2]).isEqualTo((byte) -1);
        assertThat(bytes[3]).isEqualTo((byte) -1);
    }
}
