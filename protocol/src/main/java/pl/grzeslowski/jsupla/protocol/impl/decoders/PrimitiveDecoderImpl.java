package pl.grzeslowski.jsupla.protocol.impl.decoders;

import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import static java.lang.String.format;
import static java.nio.ByteOrder.LITTLE_ENDIAN;
import static pl.grzeslowski.jsupla.Preconditions.sizeMin;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.*;

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
    public byte[] copyOfRange(final byte[] original, final int from, final int to) {
        if (to > original.length) {
            throw new IllegalArgumentException(
                format("Index 'to' (%s) is too big for array with size %s", to, original.length));
        }
        return Arrays.copyOfRange(original, from, to);
    }
}
