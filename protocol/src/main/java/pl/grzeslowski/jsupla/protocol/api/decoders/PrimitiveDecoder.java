package pl.grzeslowski.jsupla.protocol.api.decoders;


import lombok.val;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static java.lang.String.format;
import static java.nio.ByteOrder.LITTLE_ENDIAN;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.*;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.sizeMin;

public class PrimitiveDecoder {
    public static final PrimitiveDecoder INSTANCE = new PrimitiveDecoder();

    public int parseUnsignedShort(byte[] bytes, int offset) {
        sizeMin(bytes, SHORT_SIZE + offset);
        ByteBuffer bb = ByteBuffer.wrap(bytes, offset, SHORT_SIZE);
        bb.order(LITTLE_ENDIAN);
        return bb.getShort() & 0xffff;
    }

    /**
     * TODO can be optimized!!!.
     *
     * @param bytes  given bytes
     * @param offset given offset
     * @return uint
     * @see <a href="https://stackoverflow.com/q/31750160/1819402">Stack overflow answer that I've used</a>
     */
    public long parseUnsignedInt(byte[] bytes, int offset) {
        sizeMin(bytes, INT_SIZE + offset);
        ByteBuffer bb = ByteBuffer.wrap(bytes, offset, INT_SIZE);
        bb.order(LITTLE_ENDIAN);
        return bb.getInt() & 0xffffffffL;
    }

    /**
     * TODO can be optimized!!!.
     *
     * @param bytes  given bytes
     * @param offset given offset
     * @return ulong
     * @see <a href="https://stackoverflow.com/q/31750160/1819402">Stack overflow answer that I've used</a>
     */
    public BigInteger parseUnsignedLong(byte[] bytes, int offset) {
        sizeMin(bytes, LONG_SIZE + offset);
        ByteBuffer bb = ByteBuffer.wrap(bytes, offset, LONG_SIZE);
        bb.order(LITTLE_ENDIAN);

        // Read the long value from the ByteBuffer
        long signedLong = bb.getLong();

        return BigInteger.valueOf(signedLong).and(UNSIGNED_LONG_MASK);
    }

    public short parseShort(byte[] bytes, int offset) {
        sizeMin(bytes, SHORT_SIZE + offset);
        final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, offset, SHORT_SIZE);
        byteBuffer.order(LITTLE_ENDIAN);
        return byteBuffer.getShort();
    }

    public int parseInt(byte[] bytes, int offset) {
        sizeMin(bytes, INT_SIZE + offset);
        final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, offset, INT_SIZE);
        byteBuffer.order(LITTLE_ENDIAN);
        return byteBuffer.getInt();
    }

    public long parseLong(byte[] bytes, int offset) {
        sizeMin(bytes, LONG_SIZE + offset);
        final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, offset, LONG_SIZE);
        byteBuffer.order(LITTLE_ENDIAN);
        return byteBuffer.getLong();
    }


    public short parseUnsignedByte(byte[] bytes, int offset) {
        sizeMin(bytes, BYTE_SIZE + offset);
        return (short) (bytes[offset] & 0xFF);
    }


    public byte parseByte(byte[] bytes, int offset) {
        sizeMin(bytes, BYTE_SIZE + offset);
        return bytes[offset];
    }


    public char parseChar(byte[] bytes, int offset) {
        sizeMin(bytes, BYTE_SIZE + offset);
        return (char) bytes[offset];
    }

    public double parseDouble(byte[] bytes, int offset) {
        sizeMin(bytes, DOUBLE_SIZE + offset);
        val byteBuffer = ByteBuffer.wrap(bytes, offset, DOUBLE_SIZE);
        byteBuffer.order(LITTLE_ENDIAN);
        return byteBuffer.getDouble();
    }

    public byte[] copyOfRangeByte(final byte[] original, final int from, final int to) {
        if (to > original.length) {
            throw new IllegalArgumentException(
                format("Index 'to' (%s) is too big for array with size %s", to, original.length));
        }
        return Arrays.copyOfRange(original, from, to);
    }

    public short[] copyOfRangeByteUnsigned(final byte[] original, final int from, final int to) {
        if (to > original.length) {
            throw new IllegalArgumentException(
                format("Index 'to' (%s) is too big for array with size %s", to, original.length));
        }
        val result = new short[(to - from) / BYTE_SIZE];
        for (int idx = 0, offset = from; idx < result.length; idx++, offset += BYTE_SIZE) {
            result[idx] = parseUnsignedByte(original, offset);
        }
        return result;
    }

    public short[] copyOfRangeShort(byte[] original, int from, int to) {
        if (to > original.length) {
            throw new IllegalArgumentException(
                format("Index 'to' (%s) is too big for array with size %s", to, original.length));
        }
        val result = new short[(to - from) / SHORT_SIZE];
        for (int idx = 0, offset = from; idx < result.length; idx++, offset += SHORT_SIZE) {
            result[idx] = parseShort(original, offset);
        }
        return result;
    }

    public int[] copyOfRangeShortUnsigned(byte[] original, int from, int to) {
        if (to > original.length) {
            throw new IllegalArgumentException(
                format("Index 'to' (%s) is too big for array with size %s", to, original.length));
        }
        val result = new int[(to - from) / SHORT_SIZE];
        for (int idx = 0, offset = from; idx < result.length; idx++, offset += SHORT_SIZE) {
            result[idx] = parseUnsignedShort(original, offset);
        }
        return result;
    }

    public long[] copyOfRangeIntUnsigned(final byte[] original, final int from, final int to) {
        if (to > original.length) {
            throw new IllegalArgumentException(
                format("Index 'to' (%s) is too big for array with size %s", to, original.length));
        }
        val result = new long[(to - from) / INT_SIZE];
        for (int idx = 0, offset = from; idx < result.length; idx++, offset += INT_SIZE) {
            result[idx] = parseUnsignedInt(original, offset);
        }
        return result;
    }

    public int[] copyOfRangeInt(final byte[] original, final int from, final int to) {
        if (to > original.length) {
            throw new IllegalArgumentException(
                format("Index 'to' (%s) is too big for array with size %s", to, original.length));
        }
        val result = new int[(to - from) / INT_SIZE];
        for (int idx = 0, offset = from; idx < result.length; idx++, offset += INT_SIZE) {
            result[idx] = parseInt(original, offset);
        }
        return result;
    }

    public long[] copyOfRangeLong(final byte[] original, final int from, final int to) {
        if (to > original.length) {
            throw new IllegalArgumentException(
                format("Index 'to' (%s) is too big for array with size %s", to, original.length));
        }
        val result = new long[(to - from) / LONG_SIZE];
        for (int idx = 0, offset = from; idx < result.length; idx++, offset += LONG_SIZE) {
            result[idx] = parseLong(original, offset);
        }
        return result;
    }

    public BigInteger[] copyOfRangeLongUnsigned(final byte[] original, final int from, final int to) {
        if (to > original.length) {
            throw new IllegalArgumentException(
                format("Index 'to' (%s) is too big for array with size %s", to, original.length));
        }
        val result = new BigInteger[(to - from) / LONG_SIZE];
        for (int idx = 0, offset = from; idx < result.length; idx++, offset += LONG_SIZE) {
            result[idx] = parseUnsignedLong(original, offset);
        }
        return result;
    }
}
