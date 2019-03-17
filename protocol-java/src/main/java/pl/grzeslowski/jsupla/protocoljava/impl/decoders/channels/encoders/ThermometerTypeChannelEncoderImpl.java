package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ThermometerTypeChannelEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.TemperatureAndHumidityValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.TemperatureValue;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class ThermometerTypeChannelEncoderImpl implements ThermometerTypeChannelEncoder {
    @Override
    public byte[] encode(final TemperatureValue temperatureValue) {
        return new byte[SUPLA_CHANNELVALUE_SIZE]; // TODO
    }

    @Override
    public byte[] encode(final TemperatureAndHumidityValue temperatureAndHumidityValue) {
        return new byte[SUPLA_CHANNELVALUE_SIZE];// TODO
    }
}
