package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.NonNull;

/**
 * @param temperature
 * @param humidity
 */
public record TemperatureAndHumidityValue(
        @NonNull BigDecimal temperature, Optional<HumidityValue> humidity) implements ChannelValue {
    public TemperatureAndHumidityValue(BigDecimal temperature, BigDecimal humidity) {
        this(temperature, Optional.of(new HumidityValue(humidity)));
    }

    public TemperatureAndHumidityValue(BigDecimal temperature) {
        this(temperature, Optional.empty());
    }
}
