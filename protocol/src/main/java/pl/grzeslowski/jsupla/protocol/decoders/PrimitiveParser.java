package pl.grzeslowski.jsupla.protocol.decoders;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.String.format;
import static java.nio.ByteOrder.LITTLE_ENDIAN;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class PrimitiveParser {
    static int parseUnsignedInt(byte[] bytes, int offset) {
        assert bytes.length - offset >= INT_SIZE;

        final StringBuilder sb = new StringBuilder();
        for (int i = offset + INT_SIZE - 1; i >= offset; i--) {
            sb.append(binaryRepresentation(bytes[i]).replace(' ', '0'));
        }

        return MIN_VALUE + Integer.parseUnsignedInt(sb.toString(), 2);
    }

    private static String binaryRepresentation(byte bit) {
        return format("%8s", Integer.toBinaryString(bit & 0xFF));
    }

    /**
     * TODO can be optimized!!!
     */
    public static int parseInt(byte[] bytes, int offset) {
        final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, offset, INT_SIZE);
        byteBuffer.order(LITTLE_ENDIAN);
        return byteBuffer.getInt();
    }

    /**
     * TODO can be optimized!!!
     */
    public static short parseUnsignedByte(byte[] bytes, int offset) {
        byte b = bytes[offset];
        if (b < 0) {
            return (short) (b + Byte.MAX_VALUE);
        } else {
            return b;
        }
    }

    public static String parseString(byte[] bytes, int offset, int length) {
        int end = length;
        for (int i = offset; i < length; i++) {
            if (bytes[i] == (byte) 0) {
                end = i;
                break;
            }
        }
        try {
            return new String(bytes, offset, end, "ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException();
        }
    }

    public static String parseString(byte[] bytes) {
        return parseString(bytes, 0, bytes.length);
    }

    public static String parseString(char[] bytes) {
        return new String(bytes);
    }

    public static String parseUtf8String(byte[] bytes, int offset, int length) {
        int end = length;
        for (int i = offset; i < length; i++) {
            if (bytes[i] == (byte) 0) {
                end = i;
                break;
            }
        }
        try {
            return new String(bytes, offset, end, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String parseUtf8String(byte[] bytes) {
        return parseUtf8String(bytes, 0, bytes.length);
    }

    // FIXME this method cannot be here!!!
    public static String parseHexString(byte[] bytes) {
        return String.format("%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X",
                bytes[0],
                bytes[1],
                bytes[2],
                bytes[3],
                bytes[4],
                bytes[5],
                bytes[6],
                bytes[7],
                bytes[8],
                bytes[9],
                bytes[10],
                bytes[11],
                bytes[12],
                bytes[13],
                bytes[14],
                bytes[15]
        );
    }
}
