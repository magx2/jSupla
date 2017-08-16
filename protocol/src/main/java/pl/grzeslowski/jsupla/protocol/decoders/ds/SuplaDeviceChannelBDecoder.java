package pl.grzeslowski.jsupla.protocol.decoders.ds;

import pl.grzeslowski.jsupla.protocol.decoders.Decoder;
import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.ds.SuplaDeviceChannelB;

import static java.util.Arrays.copyOfRange;
import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class SuplaDeviceChannelBDecoder implements Decoder<SuplaDeviceChannelB> {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaDeviceChannelBDecoder(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaDeviceChannelB decode(byte[] bytes, int offset) {
        final short number = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final int type = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int funcList = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int defaultField = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] value = copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);
        offset += SUPLA_CHANNELVALUE_SIZE;

        return new SuplaDeviceChannelB(number, type, funcList, defaultField, value);
    }
}
