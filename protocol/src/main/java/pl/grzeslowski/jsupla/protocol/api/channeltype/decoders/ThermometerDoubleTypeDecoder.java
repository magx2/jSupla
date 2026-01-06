package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_THERMOMETER;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_THERMOMETERDS18B20;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.util.Set;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureDoubleValue;

class ThermometerDoubleTypeDecoder implements ChannelValueDecoder<TemperatureDoubleValue> {

    @Override
    public TemperatureDoubleValue decode(final byte[] bytes, final int offset) {
        val temperature = INSTANCE.parseDouble(bytes, offset);
        return new TemperatureDoubleValue(temperature);
    }

    @SuppressWarnings("deprecation")
    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(SUPLA_CHANNELTYPE_THERMOMETER, SUPLA_CHANNELTYPE_THERMOMETERDS18B20);
    }

    @Override
    public Class<TemperatureDoubleValue> getChannelValueType() {
        return TemperatureDoubleValue.class;
    }
}
