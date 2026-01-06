package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static java.math.RoundingMode.HALF_UP;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_HUMIDITYSENSOR;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Set;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.HumidityValue;

class HumidityTypeDecoder implements ChannelValueDecoder<HumidityValue> {
    private static final int MINIMAL_SIZE = INT_SIZE * 2;
    private static final int PRECISION = 6;
    private static final MathContext DIVIDE_MATH_CONTEXT = new MathContext(PRECISION, HALF_UP);
    private static final BigDecimal DIVISOR = new BigDecimal(1_000);

    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(SUPLA_CHANNELTYPE_HUMIDITYSENSOR);
    }

    @Override
    public Class<HumidityValue> getChannelValueType() {
        return HumidityValue.class;
    }

    @Override
    public HumidityValue decode(final byte[] bytes, final int offset) {
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
