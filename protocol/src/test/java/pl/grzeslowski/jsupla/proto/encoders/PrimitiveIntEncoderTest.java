package pl.grzeslowski.jsupla.proto.encoders;

import org.junit.Test;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static pl.grzeslowski.jsupla.proto.consts.JavaConsts.INT_SIZE;

public class PrimitiveIntEncoderTest {
    private static final int VALUE_THAT_I_DO_NOT_CARE = 5;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenArrayIsToSmall() {

        // given
        byte[] bytes = new byte[INT_SIZE - 1];

        // when
        PrimitiveSerializer.putUnsignedInt(VALUE_THAT_I_DO_NOT_CARE, bytes, 0);

        // then
        fail("Should throw IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenArrayIsToSmallBecauseOfOffset() {

        // given
        int offset = 3;
        byte[] bytes = new byte[offset + INT_SIZE - 1];

        // when
        PrimitiveSerializer.putUnsignedInt(VALUE_THAT_I_DO_NOT_CARE, bytes, offset);

        // then
        fail("Should throw IllegalArgumentException");
    }

    @Test
    public void shouldPutSmallUnsignedIntIntoBuffer() {

        // given
        int value = 5 + MIN_VALUE; // 5(10) is 00000101(2)
        byte[] bytes = new byte[INT_SIZE];

        // when
        PrimitiveSerializer.putUnsignedInt(value, bytes, 0);

        // then
        assertThat(bytes[0]).isEqualTo((byte) 5);
        assertThat(bytes[1]).isEqualTo((byte) 0);
        assertThat(bytes[2]).isEqualTo((byte) 0);
        assertThat(bytes[3]).isEqualTo((byte) 0);
    }

    @Test
    public void shouldPutFullUnsignedIntIntoBuffer() {

        // given
        /*
         * 2857749555 + Integer.MIN_VALUE = 710265907
         */
        int value = 710265907; // 2857749555(10) is 10101010 01010101 11001100 00110011(2)
        byte[] bytes = new byte[INT_SIZE];

        // when
        PrimitiveSerializer.putUnsignedInt(value, bytes, 0);

        // then
        assertThat(bytes[0]).isEqualTo((byte) 51);
        assertThat(bytes[1]).isEqualTo((byte) 204);
        assertThat(bytes[2]).isEqualTo((byte) 85);
        assertThat(bytes[3]).isEqualTo((byte) 170);
    }

    @Test
    public void shouldPutFullUnsignedIntIntoBufferWithOffset() {

        // given
        /*
         * 2857749555 + Integer.MIN_VALUE = 710265907
         */
        int value = 710265907; // 2857749555(10) is 10101010 01010101 11001100 00110011(2)
        int offset = 5;
        byte[] bytes = new byte[INT_SIZE + offset];

        // when
        PrimitiveSerializer.putUnsignedInt(value, bytes, offset);

        // then
        //noinspection PointlessArithmeticExpression
        assertThat(bytes[offset + 0]).isEqualTo((byte) 51);
        assertThat(bytes[offset + 1]).isEqualTo((byte) 204);
        assertThat(bytes[offset + 2]).isEqualTo((byte) 85);
        assertThat(bytes[offset + 3]).isEqualTo((byte) 170);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Test
    public void shouldPutMinimalUnsignedIntIntoBuffer() {

        // given
        int value = MIN_VALUE;
        byte[] bytes = new byte[INT_SIZE];

        // when
        PrimitiveSerializer.putUnsignedInt(value, bytes, 0);

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
        PrimitiveSerializer.putUnsignedInt(value, bytes, 0);

        // then
        assertThat(bytes[0]).isEqualTo((byte) -1);
        assertThat(bytes[1]).isEqualTo((byte) -1);
        assertThat(bytes[2]).isEqualTo((byte) -1);
        assertThat(bytes[3]).isEqualTo((byte) -1);
    }
}
