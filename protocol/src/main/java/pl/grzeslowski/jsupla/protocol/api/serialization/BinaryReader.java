package pl.grzeslowski.jsupla.protocol.api.serialization;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.Arrays;

public final class BinaryReader {
    private final byte[] bytes;
    private int offset;

    public BinaryReader(byte[] bytes) {
        this(bytes, 0);
    }

    public BinaryReader(byte[] bytes, int offset) {
        this.bytes = bytes.clone();
        if (offset < 0 || offset > bytes.length) {
            throw new ProtocolCodecException(
                    format("Offset %s is outside input bounds 0..%s", offset, bytes.length));
        }
        this.offset = offset;
    }

    public int offset() {
        return offset;
    }

    public int remaining() {
        return bytes.length - offset;
    }

    public void requireRemaining(int required, String fieldName) {
        if (required < 0) {
            throw new ProtocolCodecException("Required byte count cannot be negative");
        }
        if (remaining() < required) {
            throw new ProtocolCodecException(
                    format(
                            "Not enough bytes to read %s: required=%s, remaining=%s, offset=%s,"
                                    + " length=%s",
                            fieldName, required, remaining(), offset, bytes.length));
        }
    }

    public void requireFullyConsumed(String typeName) {
        if (remaining() != 0) {
            throw new ProtocolCodecException(
                    format(
                            "%s payload has %s trailing byte(s) after decode",
                            typeName, remaining()));
        }
    }

    public byte readInt8(String fieldName) {
        requireRemaining(1, fieldName);
        return bytes[offset++];
    }

    public short readUInt8(String fieldName) {
        return (short) (readInt8(fieldName) & 0xFF);
    }

    public int readInt32LE(String fieldName) {
        requireRemaining(4, fieldName);
        int value =
                (bytes[offset] & 0xFF)
                        | ((bytes[offset + 1] & 0xFF) << 8)
                        | ((bytes[offset + 2] & 0xFF) << 16)
                        | (bytes[offset + 3] << 24);
        offset += 4;
        return value;
    }

    public long readUInt32LE(String fieldName) {
        return readInt32LE(fieldName) & 0xFFFFFFFFL;
    }

    public byte[] readBytes(int size, String fieldName) {
        requireRemaining(size, fieldName);
        byte[] result = Arrays.copyOfRange(bytes, offset, offset + size);
        offset += size;
        return result;
    }

    public byte[] readRemainingBytes(String fieldName) {
        return readBytes(remaining(), fieldName);
    }

    public String readFixedString(int size, String fieldName) {
        return decodeFixedString(readBytes(size, fieldName));
    }

    public static String decodeFixedString(byte[] bytes) {
        int length = bytes.length;
        while (length > 0 && bytes[length - 1] == 0) {
            length--;
        }
        return new String(bytes, 0, length, UTF_8);
    }
}
