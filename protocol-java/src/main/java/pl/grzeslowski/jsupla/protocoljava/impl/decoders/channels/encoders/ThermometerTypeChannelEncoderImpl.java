package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ThermometerTypeChannelEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.TemperatureAndHumidityValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.TemperatureValue;

public class ThermometerTypeChannelEncoderImpl implements ThermometerTypeChannelEncoder {
    @Override
    public byte[] encode(final TemperatureValue temperatureValue) {
        return new byte[0];
    }

    @Override
    public byte[] encode(final TemperatureAndHumidityValue temperatureAndHumidityValue) {
        return new byte[0];
    }
}
