package pl.grzeslowski.jsupla.protocol.api.encoders;


import lombok.val;

import java.math.BigInteger;
import java.nio.ByteBuffer;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.*;

public class PrimitiveEncoder {
    public static final PrimitiveEncoder INSTANCE = new PrimitiveEncoder();

    public int writeShort(short value, byte[] bytes, int offset) {
        // TODO ca be done faster...
        byte[] integer = ByteBuffer.allocate(SHORT_SIZE).putShort(value).array();
        for (int i = 0; i < SHORT_SIZE; i++) {
            int j = SHORT_SIZE - i - 1;
            bytes[offset + i] = integer[j];
        }
        return SHORT_SIZE;
    }

    public int writeUnsignedShort(int value, byte[] bytes, int offset) {
        return writeShort((short) value, bytes, offset);
    }

    public int writeInt(int value, byte[] bytes, int offset) {
        if (bytes.length < INT_SIZE + offset) {
            throw new IllegalArgumentException(
                format("bytes length %s is too small to have int with offset %s", bytes.length, offset));
        }
        // TODO ca be done faster...
        byte[] integer = ByteBuffer.allocate(INT_SIZE).putInt(value).array();
        for (int i = 0; i < INT_SIZE; i++) {
            int j = INT_SIZE - i - 1;
            bytes[offset + i] = integer[j];
        }
        return INT_SIZE;
    }

    public int writeUnsignedInt(long value, byte[] bytes, int offset) {
        return writeInt((int) value, bytes, offset);
    }

    public int writeLong(long value, byte[] bytes, int offset) {
        if (bytes.length < LONG_SIZE + offset) {
            throw new IllegalArgumentException(format(
                "bytes length %s is too small to have int with offset %s", bytes.length, offset));
        }
        // TODO ca be done faster...
        byte[] integer = ByteBuffer.allocate(LONG_SIZE).putLong(value).array();
        for (int i = 0; i < LONG_SIZE; i++) {
            int j = LONG_SIZE - i - 1;
            bytes[offset + i] = integer[j];
        }
        return LONG_SIZE;
    }

    public int writeUnsignedLong(BigInteger value, byte[] bytes, int offset) {
        if (value.bitLength() > 64) {
            throw new IllegalArgumentException("BigInteger value is too large to fit into an unsigned long. " +
                                               "bitLength=" + value.bitLength());
        }
        return writeLong(value.longValue(), bytes, offset);
    }

    public int writeByte(byte value, byte[] bytes, int offset) {
        if (bytes.length < BYTE_SIZE + offset) {
            throw new IllegalArgumentException(
                format("bytes length %s is too small to have byte with offset %s", bytes.length, offset));
        }
        bytes[offset] = value;
        return BYTE_SIZE;
    }

    public int writeUnsignedByte(short value, byte[] bytes, int offset) {
        return writeByte((byte) value, bytes, offset);
    }

    public int writeByteArray(byte[] from, byte[] to, int toOffset) {
        System.arraycopy(from, 0, to, toOffset, from.length);
        return from.length * BYTE_SIZE;
    }

    public int writeUnsignedByteArray(short[] from, byte[] to, int toOffset) {
        for (short ubyte : from) {
            toOffset += writeUnsignedByte(ubyte, to, toOffset);
        }
        return from.length * BYTE_SIZE;
    }

    public int writeShortArray(short[] from, byte[] to, int toOffset) {
        for (short shortValue : from) {
            toOffset += writeShort(shortValue, to, toOffset);
        }
        return from.length * SHORT_SIZE;
    }

    public int writeUnsignedShortArray(int[] from, byte[] to, int toOffset) {
        for (int ushort : from) {
            toOffset += writeUnsignedShort(ushort, to, toOffset);
        }
        return from.length * SHORT_SIZE;
    }

    public int writeIntArray(int[] from, byte[] to, int toOffset) {
        for (int intValue : from) {
            toOffset += writeInt(intValue, to, toOffset);
        }
        return from.length * INT_SIZE;
    }

    @SuppressWarnings("UnusedReturnValue")
    public int writeLongArray(long[] from, byte[] to, int toOffset) {
        for (long longValue : from) {
            toOffset += writeLong(longValue, to, toOffset);
        }
        return from.length * LONG_SIZE;
    }

    @SuppressWarnings("UnusedReturnValue")
    public int writeUnsignedIntArray(long[] from, byte[] to, int toOffset) {
        for (long uint : from) {
            toOffset += writeUnsignedInt(uint, to, toOffset);
        }
        return from.length * INT_SIZE;
    }

    public int writeUnsignedLongArray(BigInteger[] from, byte[] to, int toOffset) {
        for (val longValue : from) {
            toOffset += writeUnsignedLong(longValue, to, toOffset);
        }
        return from.length * LONG_SIZE;
    }
}
