package pl.grzeslowski.jsupla.protocol.api.decoders;

import static java.lang.Integer.MAX_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.*;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.math.BigInteger;
import java.util.Random;
import lombok.val;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.JavaConsts;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;

class PrimitiveIntDecoderTest {
    Random random = new Random();

    @Test
    void shouldParseIntFromOneByte() {

        // given
        byte[] bytes = new byte[] {(byte) 5, 0, 0, 0};

        // when
        final long parseInt = INSTANCE.parseUnsignedInt(bytes, 0);

        // then
        assertThat(parseInt).isEqualTo(5);
    }

    @Test
    void shouldParseIntFromOneByteWithOffset() {

        // given
        byte[] bytes = new byte[] {1, 2, 3, (byte) 5, 0, 0, 0};

        // when
        final long parseInt = INSTANCE.parseUnsignedInt(bytes, 3);

        // then
        assertThat(parseInt).isEqualTo(5);
    }

    @Test
    void shouldParseIntFromOneByteWithBiggerArray() {

        // given
        byte[] bytes = new byte[] {1, 2, 3, (byte) 5, 0, 0, 0, 5, 6, 7, 8};

        // when
        final long parseInt = INSTANCE.parseUnsignedInt(bytes, 3);

        // then
        assertThat(parseInt).isEqualTo(5);
    }

    @Test
    void shouldParseIntFromTwoByte() {

        // given
        byte[] bytes = new byte[] {(byte) 5, 0, 0, 21};

        // when
        final long parseInt = INSTANCE.parseUnsignedInt(bytes, 0);

        // then
        assertThat(parseInt).isEqualTo(352_321_541);
    }

    @Test
    void shouldParseMaxIntFromFullInt() {

        // given
        byte[] bytes = new byte[] {(byte) -1, (byte) -1, (byte) -1, (byte) -1};

        // when
        final long parseInt = INSTANCE.parseUnsignedInt(bytes, 0);

        // then
        assertThat(parseInt).isEqualTo(MAX_VALUE * 2L + 1);
    }

    @Test
    void shouldParseMinIntFromFullInt() {

        // given
        byte[] bytes = new byte[] {(byte) 0, (byte) 0, (byte) 0, (byte) 0};

        // when
        final long parseInt = INSTANCE.parseUnsignedInt(bytes, 0);

        // then
        assertThat(parseInt).isEqualTo(0L);
    }

    @Test
    void shouldParseRangeOfBytes() {
        // given
        val expected = new byte[5];
        for (int idx = 0; idx < expected.length; idx++) {
            expected[idx] = randomByte();
        }

        val bytes = new byte[expected.length * BYTE_SIZE];
        PrimitiveEncoder.INSTANCE.writeByteArray(expected, bytes, 0);

        // when
        val result = INSTANCE.copyOfRangeByte(bytes, 0, expected.length * BYTE_SIZE);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldParseRangeOfUnsignedBytes() {
        // given
        val expected = new short[5];
        for (int idx = 0; idx < expected.length; idx++) {
            expected[idx] = randomUnsignedByte();
        }

        val bytes = new byte[expected.length * BYTE_SIZE];
        PrimitiveEncoder.INSTANCE.writeUnsignedByteArray(expected, bytes, 0);

        // when
        val result = INSTANCE.copyOfRangeByteUnsigned(bytes, 0, expected.length * BYTE_SIZE);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldParseRangeOfShorts() {
        // given
        val expected = new short[5];
        for (int idx = 0; idx < expected.length; idx++) {
            expected[idx] = randomShort();
        }

        val bytes = new byte[expected.length * SHORT_SIZE];
        PrimitiveEncoder.INSTANCE.writeShortArray(expected, bytes, 0);

        // when
        val result = INSTANCE.copyOfRangeShort(bytes, 0, expected.length * SHORT_SIZE);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldParseRangeOfUnsignedShorts() {
        // given
        val expected = new int[5];
        for (int idx = 0; idx < expected.length; idx++) {
            expected[idx] = randomUnsignedShort();
        }

        val bytes = new byte[expected.length * SHORT_SIZE];
        PrimitiveEncoder.INSTANCE.writeUnsignedShortArray(expected, bytes, 0);

        // when
        val result = INSTANCE.copyOfRangeShortUnsigned(bytes, 0, expected.length * SHORT_SIZE);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldParseRangeOfInts() {
        // given
        val expected = new int[5];
        for (int idx = 0; idx < expected.length; idx++) {
            expected[idx] = randomInt();
        }

        val bytes = new byte[expected.length * INT_SIZE];
        PrimitiveEncoder.INSTANCE.writeIntArray(expected, bytes, 0);

        // when
        val result = INSTANCE.copyOfRangeInt(bytes, 0, expected.length * INT_SIZE);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldParseRangeOfUnsignedInts() {
        // given
        val expected = new long[5];
        for (int idx = 0; idx < expected.length; idx++) {
            expected[idx] = randomUnsignedInt();
        }

        val bytes = new byte[expected.length * INT_SIZE];
        PrimitiveEncoder.INSTANCE.writeUnsignedIntArray(expected, bytes, 0);

        // when
        val result = INSTANCE.copyOfRangeIntUnsigned(bytes, 0, expected.length * INT_SIZE);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldParseRangeOfLongs() {
        // given
        val expected = new long[5];
        for (int idx = 0; idx < expected.length; idx++) {
            expected[idx] = randomLong();
        }

        val bytes = new byte[expected.length * LONG_SIZE];
        PrimitiveEncoder.INSTANCE.writeLongArray(expected, bytes, 0);

        // when
        val result = INSTANCE.copyOfRangeLong(bytes, 0, expected.length * LONG_SIZE);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldParseRangeOfUnsignedLongs() {
        // given
        val expected = new BigInteger[5];
        for (int idx = 0; idx < expected.length; idx++) {
            expected[idx] = randomUnsignedLong();
        }

        val bytes = new byte[expected.length * LONG_SIZE];
        PrimitiveEncoder.INSTANCE.writeUnsignedLongArray(expected, bytes, 0);

        // when
        val result = INSTANCE.copyOfRangeLongUnsigned(bytes, 0, expected.length * LONG_SIZE);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private byte randomByte() {
        return (byte) (random.nextInt(Byte.MAX_VALUE - Byte.MIN_VALUE) + Byte.MIN_VALUE);
    }

    private short randomUnsignedByte() {
        return (short) (random.nextInt(JavaConsts.UNSIGNED_BYTE_MAX));
    }

    private short randomShort() {
        return (short) (random.nextInt(Short.MAX_VALUE - Short.MIN_VALUE) + Short.MIN_VALUE);
    }

    private int randomUnsignedShort() {
        return random.nextInt(UNSIGNED_SHORT_MAX);
    }

    private int randomInt() {
        return random.nextInt();
    }

    private long randomUnsignedInt() {
        // Convert it to unsigned by masking with 0xFFFFFFFFL and returning as long
        return random.nextInt() & 0xFFFFFFFFL;
    }

    private long randomLong() {
        return random.nextLong();
    }

    private BigInteger randomUnsignedLong() {
        // Create an 8-byte array (64 bits)
        byte[] randomBytes = new byte[8];
        // Fill the byte array with random values
        random.nextBytes(randomBytes);
        // Convert to BigInteger as an unsigned number (positive)
        return new BigInteger(1, randomBytes); // '1' to indicate positive
    }
}
