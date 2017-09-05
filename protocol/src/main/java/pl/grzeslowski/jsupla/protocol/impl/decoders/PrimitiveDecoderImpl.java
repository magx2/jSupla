package pl.grzeslowski.jsupla.protocol.impl.decoders;

import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import static java.nio.ByteOrder.LITTLE_ENDIAN;
import static pl.grzeslowski.jsupla.Preconditions.sizeMin;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.LONG_SIZE;

public final class PrimitiveDecoderImpl implements PrimitiveDecoder {
    public static final PrimitiveDecoderImpl INSTANCE = new PrimitiveDecoderImpl();

    /**
     * TODO can be optimized!!!.
     *
     * @see <a href="https://stackoverflow.com/q/31750160/1819402">Stack overflow answer that I've used</a>
     */
    @Override
    public long parseUnsignedInt(byte[] bytes, int offset) {
        sizeMin(bytes, INT_SIZE + offset);
        ByteBuffer bb = ByteBuffer.wrap(bytes, offset, INT_SIZE);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        return bb.getInt() & 0xffffffffL;
    }

    /**
     * TODO can be optimized!!!.
     */
    @Override
    public int parseInt(byte[] bytes, int offset) {
        sizeMin(bytes, INT_SIZE + offset);
        final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, offset, INT_SIZE);
        byteBuffer.order(LITTLE_ENDIAN);
        return byteBuffer.getInt();
    }

    /**
     * TODO can be optimized!!!.
     */
    @Override
    public long parseLong(byte[] bytes, int offset) {
        sizeMin(bytes, LONG_SIZE + offset);
        final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, offset, LONG_SIZE);
        byteBuffer.order(LITTLE_ENDIAN);
        return byteBuffer.getLong();
    }

    @Override
    public short parseUnsignedByte(byte[] bytes, int offset) {
        sizeMin(bytes, BYTE_SIZE + offset);
        return (short) (bytes[offset] & 0xFF);
    }

    @Override
    public byte parseByte(byte[] bytes, int offset) {
        sizeMin(bytes, BYTE_SIZE + offset);
        return bytes[offset];
    }

    @Override
    public String parseString(byte[] bytes, int offset, int length) {
        return parseGenericString(bytes, offset, length, "ASCII");
    }

    @Override
    public String parseUtf8String(byte[] bytes, int offset, int length) {
        return parseGenericString(bytes, offset, length, "UTF-8");
    }

    /**
     * Visible only for tests.
     */
    String parseGenericString(byte[] bytes, int offset, int length, String charset) {
        sizeMin(bytes, offset + length);
        int end = length;
        for (int i = offset; i < length; i++) {
            if (bytes[i] == (byte) 0) {
                end = i - offset;
                break;
            }
        }
        try {
            return new String(bytes, offset, end, charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    // FIXME this method cannot be here!!!
    //    public String parseHexString(byte[] bytes) {
    //        return String.format("%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X%02X",
    //                bytes[0],
    //                bytes[1],
    //                bytes[2],
    //                bytes[3],
    //                bytes[4],
    //                bytes[5],
    //                bytes[6],
    //                bytes[7],
    //                bytes[8],
    //                bytes[9],
    //                bytes[10],
    //                bytes[11],
    //                bytes[12],
    //                bytes[13],
    //                bytes[14],
    //                bytes[15]
    //        );
    //    }

    @Override
    public byte[] copyOfRange(final byte[] original, final int from, final int to) {
        return Arrays.copyOfRange(original, from, to);
    }
}
