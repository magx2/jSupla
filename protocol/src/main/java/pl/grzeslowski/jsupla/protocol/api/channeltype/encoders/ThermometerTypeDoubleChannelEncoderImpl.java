package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.DOUBLE_SIZE;

import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureValue;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;

public class ThermometerTypeDoubleChannelEncoderImpl {
    public byte[] encode(final TemperatureValue temperatureValue) {
        final byte[] bytes = new byte[DOUBLE_SIZE];
        final double temp = temperatureValue.temperature().doubleValue();
        PrimitiveEncoder.INSTANCE.writeDouble(temp, bytes, 0);
        return bytes;
    }
}
