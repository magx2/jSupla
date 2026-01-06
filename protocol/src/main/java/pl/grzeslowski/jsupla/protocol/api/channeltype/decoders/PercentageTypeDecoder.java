package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_DIMMER;

import java.util.Set;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.PercentValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;

class PercentageTypeDecoder implements ChannelValueDecoder<PercentValue> {
    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(SUPLA_CHANNELTYPE_DIMMER);
    }

    @Override
    public Class<PercentValue> getChannelValueType() {
        return PercentValue.class;
    }

    @Override
    public PercentValue decode(byte[] bytes, int offset) {
        var percent = PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
        if (percent > 100 || percent < 0) {
            percent = 0;
        }
        return new PercentValue(percent);
    }
}
