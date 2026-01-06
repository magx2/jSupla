package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_RAINSENSOR;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.math.BigDecimal;
import java.util.Set;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RainValue;

class RainTypeDecoder implements ChannelValueDecoder<RainValue> {

    @Override
    public RainValue decode(final byte[] bytes, final int offset) {
        val value = INSTANCE.parseDouble(bytes, offset);
        return new RainValue(BigDecimal.valueOf(value));
    }

    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(SUPLA_CHANNELTYPE_RAINSENSOR);
    }

    @Override
    public Class<RainValue> getChannelValueType() {
        return RainValue.class;
    }
}
