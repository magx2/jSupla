package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_WEIGHTSENSOR;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.math.BigDecimal;
import java.util.Set;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.WeightValue;

class WeightTypeDecoder implements ChannelValueDecoder<WeightValue> {

    @Override
    public WeightValue decode(final byte[] bytes, final int offset) {
        val value = INSTANCE.parseDouble(bytes, offset);
        return new WeightValue(BigDecimal.valueOf(value));
    }

    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(SUPLA_CHANNELTYPE_WEIGHTSENSOR);
    }

    @Override
    public Class<WeightValue> getChannelValueType() {
        return WeightValue.class;
    }
}
