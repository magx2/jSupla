package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import java.math.BigDecimal;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.WeightValue;

public class WeightTypeEncoder extends DoubleTypeEncoder<WeightValue> {

    @Override
    protected BigDecimal mapToBigDecimal(WeightValue value) {
        return value.value();
    }
}
