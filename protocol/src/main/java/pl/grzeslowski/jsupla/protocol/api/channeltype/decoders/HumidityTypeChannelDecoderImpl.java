package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.HumidityValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureAndHumidityValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.RoundingMode.CEILING;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

class HumidityTypeChannelDecoderImpl implements Decoder<ChannelValue> {
    private static final int MINIMAL_SIZE = INT_SIZE*2;
    private static final int PRECISION = 6;
    private static final MathContext DIVIDE_MATH_CONTEXT = new MathContext(PRECISION, CEILING);
    private static final BigDecimal DIVISOR = new BigDecimal(1_000);

    @Override
    public ChannelValue decode(final byte[] bytes, final int offset) {
        Preconditions.sizeMin(bytes, offset + MINIMAL_SIZE);
        // that's not error; humidity is always as second int
        val humidityInt = INSTANCE.parseInt(bytes, offset + INT_SIZE);
        val humidity = parseDoubleValue(humidityInt);

        return new HumidityValue(humidity);
    }

    private BigDecimal parseDoubleValue(final int humidityInt) {
        return new BigDecimal(humidityInt).divide(DIVISOR, DIVIDE_MATH_CONTEXT);
    }
}
