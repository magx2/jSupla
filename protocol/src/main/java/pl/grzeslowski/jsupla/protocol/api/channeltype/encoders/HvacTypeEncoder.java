package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.HvacFlag;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.HvacValue;
import pl.grzeslowski.jsupla.protocol.api.encoders.HVACValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;

public class HvacTypeEncoder implements ChannelValueEncoder<HvacValue> {

    public static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    @Override
    public void encode(HvacValue value, byte[] bytes) {
        short on = (short) (value.on() ? 1 : 0);
        short mode = (short) value.mode().getValue();
        short heat =
                Optional.of(value)
                        .map(HvacValue::setPointTemperatureHeat)
                        .map(d -> d.multiply(ONE_HUNDRED))
                        .map(BigDecimal::shortValue)
                        .orElse((short) 0);
        short cool =
                Optional.of(value)
                        .map(HvacValue::setPointTemperatureCool)
                        .map(d -> d.multiply(ONE_HUNDRED))
                        .map(BigDecimal::shortValue)
                        .orElse((short) 0);
        val proto = new HVACValue(on, mode, heat, cool, (int) HvacFlag.toMask(value.flags()));
        HVACValueEncoder.INSTANCE.encode(proto, bytes, 0);
    }
}
