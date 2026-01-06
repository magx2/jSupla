package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_WINDSENSOR;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.math.BigDecimal;
import java.util.Set;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.WindValue;

class WindTypeDecoder implements ChannelValueDecoder<WindValue> {

    @Override
    public WindValue decode(final byte[] bytes, final int offset) {
        val value = INSTANCE.parseDouble(bytes, offset);
        return new WindValue(BigDecimal.valueOf(value));
    }

    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(SUPLA_CHANNELTYPE_WINDSENSOR);
    }

    @Override
    public Class<WindValue> getChannelValueType() {
        return WindValue.class;
    }
}
