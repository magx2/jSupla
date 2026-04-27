package pl.grzeslowski.jsupla.protocol.api.serialization;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.Arrays;

public final class BinaryWriter {
    private final byte[] bytes;
    private int offset;

    public BinaryWriter(int size) {
        if (size < 0) {
            throw new ProtocolCodecException("Output size cannot be negative");
        }
        bytes = new byte[size];
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
                            "Not enough output space to write %s: required=%s, remaining=%s,"
                                    + " offset=%s, length=%s",
                            fieldName, required, remaining(), offset, bytes.length));
        }
    }

    public void writeInt8(byte value, String fieldName) {
        requireRemaining(1, fieldName);
        bytes[offset++] = value;
    }

    public void writeUInt8(short value, String fieldName) {
        if (value < 0 || value > 0xFF) {
            throw new ProtocolCodecException(
                    format("%s value %s is outside unsigned byte range", fieldName, value));
        }
        writeInt8((byte) value, fieldName);
    }

    public void writeInt32LE(int value, String fieldName) {
        requireRemaining(4, fieldName);
        bytes[offset++] = (byte) value;
        bytes[offset++] = (byte) (value >>> 8);
        bytes[offset++] = (byte) (value >>> 16);
        bytes[offset++] = (byte) (value >>> 24);
    }

    public void writeUInt32LE(long value, String fieldName) {
        if (value < 0 || value > 0xFFFFFFFFL) {
            throw new ProtocolCodecException(
                    format("%s value %s is outside unsigned int range", fieldName, value));
        }
        writeInt32LE((int) value, fieldName);
    }

    public void writeBytes(byte[] value, String fieldName) {
        requireRemaining(value.length, fieldName);
        System.arraycopy(value, 0, bytes, offset, value.length);
        offset += value.length;
    }

    public void writeFixedBytes(byte[] value, int size, String fieldName) {
        if (value.length > size) {
            throw new ProtocolCodecException(
                    format(
                            "%s length %s is larger than fixed size %s",
                            fieldName, value.length, size));
        }
        requireRemaining(size, fieldName);
        System.arraycopy(value, 0, bytes, offset, value.length);
        offset += size;
    }

    public void writeFixedString(String value, int size, String fieldName) {
        writeFixedBytes(encodeFixedString(value, size), size, fieldName);
    }

    public byte[] toByteArray() {
        if (offset != bytes.length) {
            throw new ProtocolCodecException(
                    format(
                            "Output has not been fully written: written=%s, length=%s",
                            offset, bytes.length));
        }
        return bytes.clone();
    }

    public static byte[] encodeFixedString(String value, int size) {
        byte[] result = new byte[size];
        int outputOffset = 0;
        for (int index = 0; index < value.length(); ) {
            int codePoint = value.codePointAt(index);
            byte[] codePointBytes = new String(Character.toChars(codePoint)).getBytes(UTF_8);
            if (outputOffset + codePointBytes.length > size) {
                break;
            }
            System.arraycopy(codePointBytes, 0, result, outputOffset, codePointBytes.length);
            outputOffset += codePointBytes.length;
            index += Character.charCount(codePoint);
        }
        return result;
    }

    public static byte[] fixedBytes(byte[] value, int size) {
        if (value.length > size) {
            throw new ProtocolCodecException(
                    format("Value length %s is larger than fixed size %s", value.length, size));
        }
        return Arrays.copyOf(value, size);
    }
}
