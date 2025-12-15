package pl.grzeslowski.jsupla.protocol.api.encoders;

import static java.lang.Integer.MAX_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.LONG_SIZE;

import java.math.BigInteger;
import lombok.val;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.JavaConsts;

class PrimitiveIntEncoderTest {
    private static final int VALUE_THAT_I_DO_NOT_CARE = 5;
    private final PrimitiveEncoder primitiveEncoder = PrimitiveEncoder.INSTANCE;

    @Test
    void shouldThrowExceptionWhenArrayIsToSmall() {
        // given
        byte[] bytes = new byte[INT_SIZE - 1];

        // when / then
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    primitiveEncoder.writeUnsignedInt(VALUE_THAT_I_DO_NOT_CARE, bytes, 0);
                });
    }

    @Test
    void shouldThrowExceptionWhenArrayIsToSmallBecauseOfOffset() {
        // given
        int offset = 3;
        byte[] bytes = new byte[offset + INT_SIZE - 1];

        // when / then
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    primitiveEncoder.writeUnsignedInt(VALUE_THAT_I_DO_NOT_CARE, bytes, offset);
                });
    }

    @Test
    void shouldPutSmallUnsignedIntIntoBuffer() {

        // given
        long value = 5; // 5(10) is 00000101(2)
        byte[] bytes = new byte[INT_SIZE];

        // when
        primitiveEncoder.writeUnsignedInt(value, bytes, 0);

        // then
        assertThat(bytes[0]).isEqualTo((byte) 5);
        assertThat(bytes[1]).isEqualTo((byte) 0);
        assertThat(bytes[2]).isEqualTo((byte) 0);
        assertThat(bytes[3]).isEqualTo((byte) 0);
    }

    @Test
    void shouldPutFullUnsignedIntIntoBuffer() {

        // given
        /*
         * 2857749555 + Integer.MIN_VALUE = 710265907
         */
        long value = 2857749555L; // 2857749555(10) is 10101010 01010101 11001100 00110011(2)
        byte[] bytes = new byte[INT_SIZE];

        // when
        primitiveEncoder.writeUnsignedInt(value, bytes, 0);

        // then
        assertThat(bytes[0]).isEqualTo((byte) 51);
        assertThat(bytes[1]).isEqualTo((byte) 204);
        assertThat(bytes[2]).isEqualTo((byte) 85);
        assertThat(bytes[3]).isEqualTo((byte) 170);
    }

    @Test
    void shouldPutFullUnsignedIntIntoBufferWithOffset() {

        // given
        /*
         * 2857749555 + Integer.MIN_VALUE = 710265907
         */
        long value = 2857749555L; // 2857749555(10) is 10101010 01010101 11001100 00110011(2)
        int offset = 5;
        byte[] bytes = new byte[INT_SIZE + offset];

        // when
        primitiveEncoder.writeUnsignedInt(value, bytes, offset);

        // then
        //noinspection PointlessArithmeticExpression
        assertThat(bytes[offset + 0]).isEqualTo((byte) 51);
        assertThat(bytes[offset + 1]).isEqualTo((byte) 204);
        assertThat(bytes[offset + 2]).isEqualTo((byte) 85);
        assertThat(bytes[offset + 3]).isEqualTo((byte) 170);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Test
    void shouldPutMinimalUnsignedIntIntoBuffer() {

        // given
        long value = 0L;
        byte[] bytes = new byte[INT_SIZE];

        // when
        primitiveEncoder.writeUnsignedInt(value, bytes, 0);

        // then
        assertThat(bytes[0]).isEqualTo((byte) 0);
        assertThat(bytes[1]).isEqualTo((byte) 0);
        assertThat(bytes[2]).isEqualTo((byte) 0);
        assertThat(bytes[3]).isEqualTo((byte) 0);
    }

    @Test
    void shouldPutMaxUnsignedIntIntoBuffer() {

        // given
        long value = (long) MAX_VALUE * 2L + 1L;
        byte[] bytes = new byte[INT_SIZE];

        // when
        primitiveEncoder.writeUnsignedInt(value, bytes, 0);

        // then
        assertThat(bytes[0]).isEqualTo((byte) -1);
        assertThat(bytes[1]).isEqualTo((byte) -1);
        assertThat(bytes[2]).isEqualTo((byte) -1);
        assertThat(bytes[3]).isEqualTo((byte) -1);
    }

    @Test
    void shouldPutMinimalUnsignedLongIntoBuffer() {

        // given
        val value = BigInteger.ZERO;
        byte[] bytes = new byte[LONG_SIZE];

        // when
        primitiveEncoder.writeUnsignedLong(value, bytes, 0);

        // then
        assertThat(bytes[0]).isEqualTo((byte) 0);
        assertThat(bytes[1]).isEqualTo((byte) 0);
        assertThat(bytes[2]).isEqualTo((byte) 0);
        assertThat(bytes[3]).isEqualTo((byte) 0);
        assertThat(bytes[4]).isEqualTo((byte) 0);
        assertThat(bytes[5]).isEqualTo((byte) 0);
        assertThat(bytes[6]).isEqualTo((byte) 0);
        assertThat(bytes[7]).isEqualTo((byte) 0);
    }

    @Test
    void shouldPutMaxUnsignedLongIntoBuffer() {

        // given
        val value = JavaConsts.UNSIGNED_LONG_MAX;
        byte[] bytes = new byte[LONG_SIZE];

        // when
        primitiveEncoder.writeUnsignedLong(value, bytes, 0);

        // then
        assertThat(bytes[0]).isEqualTo((byte) -1);
        assertThat(bytes[1]).isEqualTo((byte) -1);
        assertThat(bytes[2]).isEqualTo((byte) -1);
        assertThat(bytes[3]).isEqualTo((byte) -1);
        assertThat(bytes[4]).isEqualTo((byte) -1);
        assertThat(bytes[5]).isEqualTo((byte) -1);
        assertThat(bytes[6]).isEqualTo((byte) -1);
        assertThat(bytes[7]).isEqualTo((byte) -1);
    }
}
