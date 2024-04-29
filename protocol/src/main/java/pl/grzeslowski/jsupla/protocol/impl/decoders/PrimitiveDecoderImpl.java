package pl.grzeslowski.jsupla.protocol.impl.decoders;

import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.*;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static java.lang.String.format;
import static java.nio.ByteOrder.LITTLE_ENDIAN;
import static pl.grzeslowski.jsupla.Preconditions.sizeMin;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.*;

public final class PrimitiveDecoderImpl implements PrimitiveDecoder {
    public static final PrimitiveDecoderImpl INSTANCE = new PrimitiveDecoderImpl();


    @Override
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
    @Override
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
    @Override
    public long parseUnsignedLong(byte[] bytes, int offset) {
        sizeMin(bytes, LONG_SIZE + offset);
        ByteBuffer bb = ByteBuffer.wrap(bytes, offset, LONG_SIZE);
        bb.order(LITTLE_ENDIAN);
        return bb.getLong() & 0xffffffffL;
    }

    @Override
    public short parseShort(byte[] bytes, int offset) {
        sizeMin(bytes, INT_SIZE + offset);
        final ByteBuffer byteBuffer = ByteBuffer.wrap(bytes, offset, INT_SIZE);
        byteBuffer.order(LITTLE_ENDIAN);
        return byteBuffer.getShort();
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
    public char parseChar(byte[] bytes, int offset) {
        sizeMin(bytes, BYTE_SIZE + offset);
        return (char) bytes[offset];
    }


    @Override
    public byte[] copyOfRange(final byte[] original, final int from, final int to) {
        if (to > original.length) {
            throw new IllegalArgumentException(
                format("Index 'to' (%s) is too big for array with size %s", to, original.length));
        }
        return Arrays.copyOfRange(original, from, to);
    }

    @Override
    public ElectricityMeterMeasurement parseElectricityMeterMeasurement(byte[] bytes, int offset) {
        return ElectricityMeterMeasurementDecoderImpl.INSTANCE.decode(bytes, offset);
    }

    @Override
    public SuplaChannelValueB parseSuplaChannelValueB(byte[] bytes, int offset) {
        return SuplaChannelValueBDecoderImpl.INSTANCE.decode(bytes, offset);
    }

    @Override
    public ThermostatValueGroup parseThermostatValueGroup(byte[] bytes, int offset) {
        return ThermostatValueGroupDecoderImpl.INSTANCE.decode(bytes, offset);
    }
}
