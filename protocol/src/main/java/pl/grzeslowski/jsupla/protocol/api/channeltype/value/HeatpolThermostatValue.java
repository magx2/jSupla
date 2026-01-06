package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.math.BigDecimal;
import java.util.Set;
import pl.grzeslowski.jsupla.protocol.api.ThermostatValueFlag;

public record HeatpolThermostatValue(
        boolean on,
        Set<ThermostatValueFlag> flags,
        BigDecimal measuredTemperature,
        BigDecimal presetTemperature)
        implements ChannelValue {}
