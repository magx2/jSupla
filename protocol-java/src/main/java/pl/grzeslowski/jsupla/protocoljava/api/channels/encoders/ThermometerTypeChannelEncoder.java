package pl.grzeslowski.jsupla.protocoljava.api.channels.encoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.values.TemperatureAndHumidityValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.TemperatureValue;

public interface ThermometerTypeChannelEncoder {
    byte[] encode(TemperatureValue temperatureValue);

    byte[] encode(TemperatureAndHumidityValue temperatureAndHumidityValue);
}
