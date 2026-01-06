package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import java.math.BigDecimal;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.HumidityValue;
import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;

public class HumidityTypeChannelEncoderImpl {
    private static final BigDecimal MULTIPLIER = new BigDecimal(1_000);

    public byte[] encode(final HumidityValue humidityValue) {
        final byte[] bytes = new byte[SUPLA_CHANNELVALUE_SIZE];
        final int humidity = humidityValue.humidity().multiply(MULTIPLIER).intValue();
        // that's not error; humidity is always as second int
        PrimitiveEncoder.INSTANCE.writeInt(humidity, bytes, INT_SIZE);
        return bytes;
    }
}
