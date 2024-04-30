package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureAndHumidityValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.RoundingMode.CEILING;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

class ThermometerTypeChannelDecoderImpl implements Decoder<ChannelValue> {
    private static final int MINIMAL_SIZE = INT_SIZE;
    private static final int PRECISION = 6;
    private static final MathContext DIVIDE_MATH_CONTEXT = new MathContext(PRECISION, CEILING);
    private static final BigDecimal DIVISOR = new BigDecimal(1_000);

    @Override
    public ChannelValue decode(final byte[] bytes, final int offset) {
        Preconditions.sizeMin(bytes, offset + MINIMAL_SIZE);
        final int temperatureInt = INSTANCE.parseInt(bytes, offset);
        final BigDecimal temperature = parseDoubleValue(temperatureInt);
        if (bytes.length < offset + INT_SIZE * 2) {
            return new TemperatureValue(temperature);
        }
        final int humidityInt = INSTANCE.parseInt(bytes, offset + INT_SIZE);
        final BigDecimal humidity = parseDoubleValue(humidityInt);
        return new TemperatureAndHumidityValue(temperature, humidity);
    }

    private BigDecimal parseDoubleValue(final int humidityInt) {
        return new BigDecimal(humidityInt).divide(DIVISOR, DIVIDE_MATH_CONTEXT);
    }
}
