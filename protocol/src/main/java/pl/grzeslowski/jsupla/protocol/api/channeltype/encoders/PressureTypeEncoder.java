package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import java.math.BigDecimal;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.PressureValue;

public class PressureTypeEncoder extends DoubleTypeEncoder<PressureValue> {

    @Override
    protected BigDecimal mapToBigDecimal(PressureValue value) {
        return value.value();
    }
}
