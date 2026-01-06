package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import java.math.BigDecimal;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.WindValue;

public class WindTypeEncoder extends DoubleTypeEncoder<WindValue> {

    @Override
    protected BigDecimal mapToBigDecimal(WindValue value) {
        return value.value();
    }
}
