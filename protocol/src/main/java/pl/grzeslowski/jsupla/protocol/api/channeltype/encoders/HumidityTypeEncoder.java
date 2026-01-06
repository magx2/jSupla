package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

import java.math.BigDecimal;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.HumidityValue;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;

public class HumidityTypeEncoder implements ChannelValueEncoder<HumidityValue> {
    private static final BigDecimal MULTIPLIER = new BigDecimal(1_000);

    @Override
    public void encode(HumidityValue value, byte[] bytes) {
        final int humidity = value.humidity().multiply(MULTIPLIER).intValue();
        // that's not error; humidity is always as second int
        PrimitiveEncoder.INSTANCE.writeInt(humidity, bytes, INT_SIZE);
    }
}
