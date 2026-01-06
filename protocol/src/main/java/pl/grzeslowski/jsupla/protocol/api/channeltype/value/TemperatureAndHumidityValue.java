package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.math.BigDecimal;

/**
 * @param temperature
 * @param humidity
 */
public record TemperatureAndHumidityValue(TemperatureValue temperature, HumidityValue humidity)
        implements ChannelValue {
    public TemperatureAndHumidityValue(BigDecimal temp, BigDecimal humidity) {
        this(new TemperatureValue(temp), new HumidityValue(humidity));
    }
}
