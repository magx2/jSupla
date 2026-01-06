package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static java.math.RoundingMode.HALF_EVEN;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.SUPLA_CHANNELTYPE_THERMOSTAT_HEATPOL_HOMEPLUS;

import java.math.BigDecimal;
import java.util.Set;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.ThermostatValueFlag;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.HeatpolThermostatValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.ThermostatValueDecoder;

public class HeatpolThermostatTypeDecoder implements ChannelValueDecoder<HeatpolThermostatValue> {
    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(SUPLA_CHANNELTYPE_THERMOSTAT_HEATPOL_HOMEPLUS);
    }

    @Override
    public Class<HeatpolThermostatValue> getChannelValueType() {
        return HeatpolThermostatValue.class;
    }

    @Override
    public HeatpolThermostatValue decode(byte[] bytes, int offset) {
        var decode = ThermostatValueDecoder.INSTANCE.decode(bytes, offset);
        return new HeatpolThermostatValue(
                decode.isOn() > 0,
                ThermostatValueFlag.findByMask(decode.flags()),
                toBigDecimal(decode.measuredTemperature()),
                toBigDecimal(decode.presetTemperature()));
    }

    private BigDecimal toBigDecimal(short value) {
        return BigDecimal.valueOf(value).setScale(2, HALF_EVEN).divide(ONE_HUNDRED, HALF_EVEN);
    }
}
