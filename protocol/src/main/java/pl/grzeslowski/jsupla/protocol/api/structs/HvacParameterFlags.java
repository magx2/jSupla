package pl.grzeslowski.jsupla.protocol.api.structs;

public record HvacParameterFlags(
        boolean mainThermometerChannelNoReadonly,
        boolean mainThermometerChannelNoHidden,
        boolean auxThermometerChannelNoReadonly,
        boolean auxThermometerChannelNoHidden,
        boolean binarySensorChannelNoReadonly,
        boolean binarySensorChannelNoHidden,
        boolean auxThermometerTypeReadonly,
        boolean auxThermometerTypeHidden,
        // 1
        boolean antiFreezeAndOverheatProtectionEnabledReadonly,
        boolean antiFreezeAndOverheatProtectionEnabledHidden,
        boolean usedAlgorithmReadonly,
        boolean usedAlgorithmHidden,
        boolean minOnTimeSReadonly,
        boolean minOnTimeSHidden,
        boolean minOffTimeSReadonly,
        boolean minOffTimeSHidden,
        // 2
        boolean outputValueOnErrorReadonly,
        boolean outputValueOnErrorHidden,
        boolean subfunctionReadonly,
        boolean subfunctionHidden,
        boolean temperatureSetpointChangeSwitchesToManualModeReadonly,
        boolean temperatureSetpointChangeSwitchesToManualModeHidden,
        boolean auxMinMaxSetpointEnabledReadonly,
        boolean auxMinMaxSetpointEnabledHidden,
        // 3
        boolean useSeparateHeatCoolOutputsReadonly,
        boolean useSeparateHeatCoolOutputsHidden,
        boolean temperaturesFreezeProtectionReadonly,
        boolean temperaturesFreezeProtectionHidden,
        boolean temperaturesEcoReadonly,
        boolean temperaturesEcoHidden,
        boolean temperaturesComfortReadonly,
        boolean temperaturesComfortHidden,
        // 4
        boolean temperaturesBoostReadonly,
        boolean temperaturesBoostHidden,
        boolean temperaturesHeatProtectionReadonly,
        boolean temperaturesHeatProtectionHidden,
        boolean temperaturesHisteresisReadonly,
        boolean temperaturesHisteresisHidden,
        boolean temperaturesBelowAlarmReadonly,
        boolean temperaturesBelowAlarmHidden,
        // 5
        boolean temperaturesAboveAlarmReadonly,
        boolean temperaturesAboveAlarmHidden,
        boolean temperaturesAuxMinSetpointReadonly,
        boolean temperaturesAuxMinSetpointHidden,
        boolean temperaturesAuxMaxSetpointReadonly,
        boolean temperaturesAuxMaxSetpointHidden,
        boolean masterThermostatChannelNoReadonly,
        boolean masterThermostatChannelNoHidden,
        // 6
        boolean heatOrColdSourceSwitchReadonly,
        boolean heatOrColdSourceSwitchHidden,
        boolean pumpSwitchReadonly,
        boolean pumpSwitchHidden)
        implements pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize {
    public static final int SIZE = 8;

    @Override
    public int protoSize() {
        return SIZE;
    }
}
