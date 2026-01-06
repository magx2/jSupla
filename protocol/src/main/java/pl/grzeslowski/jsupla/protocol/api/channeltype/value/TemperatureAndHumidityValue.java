package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import lombok.NonNull;

import java.math.BigDecimal;

/**
 * @param temperature
 * @param humidity
 */
public record TemperatureAndHumidityValue(@NonNull TemperatureValue temperature,@NonNull  HumidityValue humidity)
        implements ChannelValue {
    public TemperatureAndHumidityValue(BigDecimal temp, BigDecimal humidity) {
        this(new TemperatureValue(temp), new HumidityValue(humidity));
    }
}
