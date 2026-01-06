package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import java.math.BigDecimal;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureAndHumidityValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureValue;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;

public class ThermometerTypeChannelEncoderImpl {
    private static final BigDecimal MULTIPLIER = new BigDecimal(1_000);

    public byte[] encode(final TemperatureValue temperatureValue) {
        final byte[] bytes = new byte[SUPLA_CHANNELVALUE_SIZE];
        final int temp = temperatureValue.temperature().multiply(MULTIPLIER).intValue();
        PrimitiveEncoder.INSTANCE.writeInt(temp, bytes, 0);
        return bytes;
    }

    public byte[] encode(final TemperatureAndHumidityValue temperatureAndHumidityValue) {
        final byte[] bytes = new byte[SUPLA_CHANNELVALUE_SIZE];
        final int temp = temperatureAndHumidityValue.temperature().temperature().multiply(MULTIPLIER).intValue();
        final int humidity = temperatureAndHumidityValue.humidity().humidity().multiply(MULTIPLIER).intValue();
        PrimitiveEncoder.INSTANCE.writeInt(temp, bytes, 0);
        PrimitiveEncoder.INSTANCE.writeInt(humidity, bytes, INT_SIZE);
        return bytes;
    }
}
