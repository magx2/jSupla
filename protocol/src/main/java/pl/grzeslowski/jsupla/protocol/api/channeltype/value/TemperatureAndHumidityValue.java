package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.math.BigDecimal;
import lombok.NonNull;

/**
 * @param temperature
 * @param humidity
 */
public record TemperatureAndHumidityValue(
        @NonNull BigDecimal temperature, @NonNull HumidityValue humidity) implements ChannelValue {
    public TemperatureAndHumidityValue(BigDecimal temperature, BigDecimal humidity) {
        this(temperature, new HumidityValue(humidity));
    }
}
