package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static java.math.RoundingMode.HALF_UP;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.*;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Set;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureAndHumidityValue;

class ThermometerTypeDecoder implements ChannelValueDecoder<TemperatureAndHumidityValue> {
    private static final int MINIMAL_SIZE = INT_SIZE;
    private static final int PRECISION = 6;
    private static final MathContext DIVIDE_MATH_CONTEXT = new MathContext(PRECISION, HALF_UP);
    private static final BigDecimal DIVISOR = new BigDecimal(1_000);

    @Override
    public TemperatureAndHumidityValue decode(final byte[] bytes, final int offset) {
        Preconditions.sizeMin(bytes, offset + MINIMAL_SIZE);
        val temperatureInt = INSTANCE.parseInt(bytes, offset);
        val temperature = parseDoubleValue(temperatureInt);

        val humidityInt = INSTANCE.parseInt(bytes, offset + INT_SIZE);
        val humidity = parseDoubleValue(humidityInt);

        return new TemperatureAndHumidityValue(temperature, humidity);
    }

    private BigDecimal parseDoubleValue(final int humidityInt) {
        return new BigDecimal(humidityInt).divide(DIVISOR, DIVIDE_MATH_CONTEXT);
    }

    @SuppressWarnings("deprecation")
    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(
                SUPLA_CHANNELTYPE_DHT11,
                SUPLA_CHANNELTYPE_DHT22,
                SUPLA_CHANNELTYPE_DHT21,
                SUPLA_CHANNELTYPE_AM2302,
                SUPLA_CHANNELTYPE_AM2301,
                SUPLA_CHANNELTYPE_HUMIDITYANDTEMPSENSOR);
    }

    @Override
    public Class<TemperatureAndHumidityValue> getChannelValueType() {
        return TemperatureAndHumidityValue.class;
    }
}
