package pl.grzeslowski.jsupla.protocol.impl.decoders;

import pl.grzeslowski.jsupla.protocol.decoders.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.structs.SuplaChannelValue;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class SuplaChannelValueDecoderImpl implements SuplaChannelValueDecoder {
    @Override
    public SuplaChannelValue decode(byte[] bytes, int offset) {
        final byte[] value = Arrays.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);
        offset += SUPLA_CHANNELVALUE_SIZE;

        final byte[] subValue = Arrays.copyOfRange(bytes, offset, offset + SUPLA_CHANNELVALUE_SIZE);

        return new SuplaChannelValue(value, subValue);
    }
}
