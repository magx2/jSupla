package pl.grzeslowski.jsupla.protocol.api.decoders;


import lombok.val;

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
     * @see <a href="https://stackoverflow.com/q/31750160/1819402">Stack overflow answer that I've used</a>
     */

    public long parseUnsignedLong(byte[] bytes, int offset) {
        sizeMin(bytes, LONG_SIZE + offset);
        ByteBuffer bb = ByteBuffer.wrap(bytes, offset, LONG_SIZE);
        bb.order(LITTLE_ENDIAN);
        return bb.getLong() & 0xffffffffL;
    }


    public short parseShort(byte[] bytes, int offset) {
        sizeMin(bytes, INT_SIZE + offset);
        final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, offset, INT_SIZE);
        byteBuffer.order(LITTLE_ENDIAN);
        return byteBuffer.getShort();
    }

    /**
     * TODO can be optimized!!!.
     */

    public int parseInt(byte[] bytes, int offset) {
        sizeMin(bytes, INT_SIZE + offset);
        final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, offset, INT_SIZE);
        byteBuffer.order(LITTLE_ENDIAN);
        return byteBuffer.getInt();
    }

    /**
     * TODO can be optimized!!!.
     */

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
        val result = new short[to - from];
        for (int i = from; i < to; i += BYTE_SIZE) {
            result[i - from] = parseUnsignedByte(original, i);
        }
        return result;
    }

    public short[] copyOfRangeShort(byte[] original, int from, int to) {
        if (to > original.length) {
            throw new IllegalArgumentException(
                format("Index 'to' (%s) is too big for array with size %s", to, original.length));
        }
        val result = new short[to - from];
        for (int i = from; i < to; i += SHORT_SIZE) {
            result[i - from] = parseShort(original, i);
        }
        return result;
    }

    public int[] copyOfRangeShortUnsigned(byte[] original, int from, int to) {
        if (to > original.length) {
            throw new IllegalArgumentException(
                format("Index 'to' (%s) is too big for array with size %s", to, original.length));
        }
        val result = new int[to - from];
        for (int i = from; i < to; i += SHORT_SIZE) {
            result[i - from] = parseUnsignedShort(original, i);
        }
        return result;
    }

    public long[] copyOfRangeIntUnsigned(final byte[] original, final int from, final int to) {
        if (to > original.length) {
            throw new IllegalArgumentException(
                format("Index 'to' (%s) is too big for array with size %s", to, original.length));
        }
        val result = new long[to - from];
        for (int i = from; i < to; i += INT_SIZE) {
            result[i - from] = parseUnsignedInt(original, i);
        }
        return result;
    }

    public int[] copyOfRangeInt(final byte[] original, final int from, final int to) {
        if (to > original.length) {
            throw new IllegalArgumentException(
                format("Index 'to' (%s) is too big for array with size %s", to, original.length));
        }
        val result = new int[to - from];
        for (int i = from; i < to; i += INT_SIZE) {
            result[i - from] = parseInt(original, i);
        }
        return result;
    }

    public long[] copyOfRangeLongUnsigned(final byte[] original, final int from, final int to) {
        if (to > original.length) {
            throw new IllegalArgumentException(
                format("Index 'to' (%s) is too big for array with size %s", to, original.length));
        }
        val result = new long[to - from];
        for (int i = from; i < to; i += LONG_SIZE) {
            result[i - from] = parseUnsignedLong(original, i);
        }
        return result;
    }
}
