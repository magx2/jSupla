package pl.grzeslowski.jsupla.protocol.impl.encoders;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;

import java.nio.ByteBuffer;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.*;

public final class PrimitiveEncoderImpl implements PrimitiveEncoder {
    public static final PrimitiveEncoderImpl INSTANCE = new PrimitiveEncoderImpl();

    @Override
    public int writeInteger(int value, byte[] bytes, int offset) {
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

    @Override
    public int writeUnsignedInteger(long value, byte[] bytes, int offset) {
        return writeInteger((int) value, bytes, offset);
    }

    @Override
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

    @Override
    public int writeByte(byte value, byte[] bytes, int offset) {
        if (bytes.length < BYTE_SIZE + offset) {
            throw new IllegalArgumentException(
                format("bytes length %s is too small to have byte with offset %s", bytes.length, offset));
        }
        bytes[offset] = value;
        return BYTE_SIZE;
    }

    @Override
    public int writeUnsignedByte(short value, byte[] bytes, int offset) {
        return writeByte((byte) value, bytes, offset);
    }

    @Override
    public int writeBytes(byte[] from, byte[] to, int toOffset) {
        for (int i = 0; i < from.length; i++) {
            to[toOffset + i] = from[i];
        }
        return from.length;
    }
}
