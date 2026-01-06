package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import java.math.BigDecimal;
import pl.grzeslowski.jsupla.protocol.api.ThermostatValueFlag;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.HeatpolThermostatValue;
import pl.grzeslowski.jsupla.protocol.api.encoders.ThermostatValueEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.ThermostatValue;

class HeatpolThermostatTypeEncoder implements ChannelValueEncoder<HeatpolThermostatValue> {
    public static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    @Override
    public void encode(HeatpolThermostatValue value, byte[] bytes) {
        var proto =
                new ThermostatValue(
                        (short) (value.on() ? 1 : 0),
                        (short) ThermostatValueFlag.toMask(value.flags()),
                        bigDecimalToShort(value.measuredTemperature()),
                        bigDecimalToShort(value.presetTemperature()));
        ThermostatValueEncoder.INSTANCE.encode(proto, bytes, 0);
    }

    private short bigDecimalToShort(BigDecimal value) {
        return value.multiply(ONE_HUNDRED).shortValueExact();
    }
}
