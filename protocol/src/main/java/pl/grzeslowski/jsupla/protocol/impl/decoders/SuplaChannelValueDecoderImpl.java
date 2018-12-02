package pl.grzeslowski.jsupla.protocol.impl.decoders;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.impl.decoders.sc.SuplaChannelDecoderImpl;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

public final class SuplaChannelValueDecoderImpl implements SuplaChannelValueDecoder {
    public static final SuplaChannelValueDecoderImpl INSTANCE = new SuplaChannelValueDecoderImpl();
    
    @Override
    public SuplaChannelValue decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaChannelValue.SIZE);

        final byte[] value = PrimitiveDecoderImpl.INSTANCE.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);
        offset += SUPLA_CHANNELVALUE_SIZE;

        final byte[] subValue = PrimitiveDecoderImpl.INSTANCE.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaChannelValue(value, subValue);
    }
}
