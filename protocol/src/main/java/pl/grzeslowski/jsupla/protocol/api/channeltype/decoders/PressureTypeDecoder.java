package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.*;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.math.BigDecimal;
import java.util.Set;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.PressureValue;

class PressureTypeDecoder implements ChannelValueDecoder<PressureValue> {

    @Override
    public PressureValue decode(final byte[] bytes, final int offset) {
        val value = INSTANCE.parseDouble(bytes, offset);
        return new PressureValue(BigDecimal.valueOf(value));
    }

    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(SUPLA_CHANNELTYPE_PRESSURESENSOR);
    }

    @Override
    public Class<PressureValue> getChannelValueType() {
        return PressureValue.class;
    }
}
