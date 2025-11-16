package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import java.util.Optional;
import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.HvacValue;
import pl.grzeslowski.jsupla.protocol.api.encoders.HVACValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.HVACValue;

public class HvacChannelEncoderImpl {
    public byte[] encode(final HvacValue value) {
        short on = (short) (value.on() ? 1 : 0);
        short mode = (short) value.mode().getMask();
        short heat =
                Optional.of(value)
                        .map(HvacValue::setPointTemperatureHeat)
                        .map(d -> d * 100)
                        .map(Double::shortValue)
                        .orElse((short) 0);
        short cool =
                Optional.of(value)
                        .map(HvacValue::setPointTemperatureCool)
                        .map(d -> d * 100)
                        .map(Double::shortValue)
                        .orElse((short) 0);
        val proto = new HVACValue(on, mode, heat, cool, value.flags().toInt());
        return HVACValueEncoder.INSTANCE.encode(proto);
    }
}
