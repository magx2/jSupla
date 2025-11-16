package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static java.math.RoundingMode.CEILING;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.math.BigDecimal;
import java.math.MathContext;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureAndHumidityValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;

class ThermometerTypeChannelDecoderImpl implements Decoder<ChannelValue> {
    private static final int MINIMAL_SIZE = INT_SIZE;
    private static final int PRECISION = 6;
    private static final MathContext DIVIDE_MATH_CONTEXT = new MathContext(PRECISION, CEILING);
    private static final BigDecimal DIVISOR = new BigDecimal(1_000);

    @Override
    public ChannelValue decode(final byte[] bytes, final int offset) {
        Preconditions.sizeMin(bytes, offset + MINIMAL_SIZE);
        val temperatureInt = INSTANCE.parseInt(bytes, offset);
        val temperature = parseDoubleValue(temperatureInt);

        if (bytes.length < offset + INT_SIZE * 2) {
            return new TemperatureValue(temperature);
        }
        val humidityInt = INSTANCE.parseInt(bytes, offset + INT_SIZE);
        val humidity = parseDoubleValue(humidityInt);

        return new TemperatureAndHumidityValue(temperature, humidity);
    }

    private BigDecimal parseDoubleValue(final int humidityInt) {
        return new BigDecimal(humidityInt).divide(DIVISOR, DIVIDE_MATH_CONTEXT);
    }
}
