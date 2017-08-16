package pl.grzeslowski.jsupla.protocol.impl.decoders.ds;

import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.ds.SuplaDeviceChannelDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

@Deprecated
public final class SuplaDeviceChannelDecoderImpl implements SuplaDeviceChannelDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaDeviceChannelDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaDeviceChannel decode(byte[] bytes, int offset) {
        final short number = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final int type = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] value = primitiveDecoder.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaDeviceChannel(number, type, value);
    }
}
