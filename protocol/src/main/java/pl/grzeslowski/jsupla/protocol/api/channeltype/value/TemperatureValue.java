package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class TemperatureValue implements ChannelValue {
    BigDecimal temperature;

    public TemperatureValue(final int value) {
        this(new BigDecimal(value));
    }

    public TemperatureValue(final long value) {
        this(new BigDecimal(value));
    }

    public TemperatureValue(final double value) {
        this(new BigDecimal(value));
    }

    public TemperatureValue(final float value) {
        this(new BigDecimal(value));
    }

    public TemperatureValue(final byte value) {
        this(new BigDecimal(value));
    }

    public TemperatureValue(final short value) {
        this(new BigDecimal(value));
    }

    public TemperatureValue(final String value) {
        this(new BigDecimal(value));
    }
}
