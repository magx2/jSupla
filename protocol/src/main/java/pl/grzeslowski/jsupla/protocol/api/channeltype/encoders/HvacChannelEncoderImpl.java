package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;


import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.HvacValue;
import pl.grzeslowski.jsupla.protocol.api.encoders.HVACValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;

import java.util.Optional;

public class HvacChannelEncoderImpl {
    public byte[] encode(final HvacValue value) {
        short on = (short) (value.isOn() ? 1 : 0);
        short mode = (short) value.getMode().getMask();
        short heat = Optional.of(value)
            .map(HvacValue::getSetPointTemperatureHeat)
            .map(d -> d * 100)
            .map(Double::shortValue)
            .orElse((short) 0);
        short cool = Optional.of(value)
            .map(HvacValue::getSetPointTemperatureCool)
            .map(d -> d * 100)
            .map(Double::shortValue)
            .orElse((short) 0);
        int flags;
        val proto = new HVACValue(on, mode, heat, cool, value.getFlags().toInt());
        return HVACValueEncoder.INSTANCE.encode(proto);
    }
}
