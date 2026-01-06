package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.math.BigDecimal;
import lombok.NonNull;

/**
 * @param temperature
 */
public record TemperatureDoubleValue(@NonNull BigDecimal temperature) implements ChannelValue {
    public TemperatureDoubleValue(final double value) {
        this(BigDecimal.valueOf(value));
    }
}
