package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import java.math.BigDecimal;
import lombok.NonNull;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.TemperatureValue;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;

class TemperatureTypeEncoder implements ChannelValueEncoder<TemperatureValue> {
    private static final BigDecimal MULTIPLIER = new BigDecimal(1_000);

    @Override
    public void encode(TemperatureValue value, byte[] bytes) {
        var temp = encodeDouble(value.temperature());
        PrimitiveEncoder.INSTANCE.writeInt(temp, bytes, 0);
    }

    private static int encodeDouble(@NonNull BigDecimal value) {
        return value.multiply(MULTIPLIER).intValue();
    }
}
