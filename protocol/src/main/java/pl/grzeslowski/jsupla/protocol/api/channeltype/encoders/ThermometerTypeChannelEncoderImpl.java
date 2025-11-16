package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureAndHumidityValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureValue;

public class ThermometerTypeChannelEncoderImpl {
    public byte[] encode(final TemperatureValue temperatureValue) {
        return new byte[SUPLA_CHANNELVALUE_SIZE]; // TODO
    }

    public byte[] encode(final TemperatureAndHumidityValue temperatureAndHumidityValue) {
        return new byte[SUPLA_CHANNELVALUE_SIZE]; // TODO
    }
}
