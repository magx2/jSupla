package pl.grzeslowski.jsupla.proto.encoders;

import java.nio.ByteBuffer;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;

final class PrimitiveEncoder {
    static int writeInteger(int value, byte[] bytes, int offset) {
        if (bytes.length < INT_SIZE + offset) {
            throw new IllegalArgumentException(format("bytes length %s is too small to have int with offset %s", bytes.length, offset));
        }
        // TODO ca be done faster...
        byte[] integer = ByteBuffer.allocate(INT_SIZE).putInt(value).array();
        for (int i = 0; i < INT_SIZE; i++) {
            int j = INT_SIZE - i;
            bytes[offset + i] = integer[j];
        }
        return INT_SIZE;
    }

    static int writeByte(byte value, byte[] bytes, int offset) {
        if (bytes.length < BYTE_SIZE + offset) {
            throw new IllegalArgumentException(format("bytes length %s is too small to have byte with offset %s", bytes.length, offset));
        }
        bytes[offset] = value;
        return BYTE_SIZE;
    }
}
