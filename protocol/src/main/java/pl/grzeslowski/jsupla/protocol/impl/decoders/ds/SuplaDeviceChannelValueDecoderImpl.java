package pl.grzeslowski.jsupla.protocol.impl.decoders.ds;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaDeviceChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;
import pl.grzeslowski.jsupla.protocol.impl.decoders.cs.SuplaChannelNewValueBDecoderImpl;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class SuplaDeviceChannelValueDecoderImpl implements SuplaDeviceChannelValueDecoder {
    public static final SuplaDeviceChannelValueDecoderImpl INSTANCE = new SuplaDeviceChannelValueDecoderImpl(
            PrimitiveDecoderImpl.INSTANCE);
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaDeviceChannelValueDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaDeviceChannelValue decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaDeviceChannelValue.SIZE);

        final short channelNumber = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final byte[] value = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaDeviceChannelValue(channelNumber, value);
    }
}
