package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import java.math.BigDecimal;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureDoubleValue;

class ThermometerDoubleTypeEncoder extends DoubleTypeEncoder<TemperatureDoubleValue> {

    @Override
    protected BigDecimal mapToBigDecimal(TemperatureDoubleValue value) {
        return value.temperature();
    }
}
