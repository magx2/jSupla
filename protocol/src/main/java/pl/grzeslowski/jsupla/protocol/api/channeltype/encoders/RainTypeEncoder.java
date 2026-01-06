package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import java.math.BigDecimal;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RainValue;

public class RainTypeEncoder extends DoubleTypeEncoder<RainValue> {

    @Override
    protected BigDecimal mapToBigDecimal(RainValue value) {
        return value.value();
    }
}
