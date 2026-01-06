package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

import java.math.BigDecimal;
import lombok.NonNull;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureAndHumidityValue;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;

public class ThermometerTypeEncoder implements ChannelValueEncoder<TemperatureAndHumidityValue> {
    private static final BigDecimal MULTIPLIER = new BigDecimal(1_000);

    @Override
    public void encode(TemperatureAndHumidityValue value, byte[] bytes) {
        var temp = encodeDouble(value.temperature());
        PrimitiveEncoder.INSTANCE.writeInt(temp, bytes, 0);

        var humidity = encodeDouble(value.humidity().humidity());
        PrimitiveEncoder.INSTANCE.writeInt(humidity, bytes, INT_SIZE);
    }

    private static int encodeDouble(@NonNull BigDecimal value) {
        return value.multiply(MULTIPLIER).intValue();
    }
}
