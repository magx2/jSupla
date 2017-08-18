package pl.grzeslowski.jsupla.protocol.impl.decoders.ds;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaDeviceChannelBDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class SuplaDeviceChannelBDecoderImpl implements SuplaDeviceChannelBDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaDeviceChannelBDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaDeviceChannelB decode(byte[] bytes, int offset) {
        Preconditions.checkMinArrayLength(bytes, offset + SuplaDeviceChannelB.SIZE);

        final short number = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final int type = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int funcList = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int defaultField = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] value = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);
        offset += SUPLA_CHANNELVALUE_SIZE;

        return new SuplaDeviceChannelB(number, type, funcList, defaultField, value);
    }
}
