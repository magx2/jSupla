package pl.grzeslowski.jsupla.protocol.api.structs;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class HvacParameterFlags implements pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize {
    public static final int SIZE = 8;

    //0
    public final boolean mainThermometerChannelNoReadonly;
    public final boolean mainThermometerChannelNoHidden;
    public final boolean auxThermometerChannelNoReadonly;
    public final boolean auxThermometerChannelNoHidden;
    public final boolean binarySensorChannelNoReadonly;
    public final boolean binarySensorChannelNoHidden;
    public final boolean auxThermometerTypeReadonly;
    public final boolean auxThermometerTypeHidden;
    //1
    public final boolean antiFreezeAndOverheatProtectionEnabledReadonly;
    public final boolean antiFreezeAndOverheatProtectionEnabledHidden;
    public final boolean usedAlgorithmReadonly;
    public final boolean usedAlgorithmHidden;
    public final boolean minOnTimeSReadonly;
    public final boolean minOnTimeSHidden;
    public final boolean minOffTimeSReadonly;
    public final boolean minOffTimeSHidden;
    //2
    public final boolean outputValueOnErrorReadonly;
    public final boolean outputValueOnErrorHidden;
    public final boolean subfunctionReadonly;
    public final boolean subfunctionHidden;
    public final boolean temperatureSetpointChangeSwitchesToManualModeReadonly;
    public final boolean temperatureSetpointChangeSwitchesToManualModeHidden;
    public final boolean auxMinMaxSetpointEnabledReadonly;
    public final boolean auxMinMaxSetpointEnabledHidden;
    //3
    public final boolean useSeparateHeatCoolOutputsReadonly;
    public final boolean useSeparateHeatCoolOutputsHidden;
    public final boolean temperaturesFreezeProtectionReadonly;
    public final boolean temperaturesFreezeProtectionHidden;
    public final boolean temperaturesEcoReadonly;
    public final boolean temperaturesEcoHidden;
    public final boolean temperaturesComfortReadonly;
    public final boolean temperaturesComfortHidden;
    //4
    public final boolean temperaturesBoostReadonly;
    public final boolean temperaturesBoostHidden;
    public final boolean temperaturesHeatProtectionReadonly;
    public final boolean temperaturesHeatProtectionHidden;
    public final boolean temperaturesHisteresisReadonly;
    public final boolean temperaturesHisteresisHidden;
    public final boolean temperaturesBelowAlarmReadonly;
    public final boolean temperaturesBelowAlarmHidden;
    //5
    public final boolean temperaturesAboveAlarmReadonly;
    public final boolean temperaturesAboveAlarmHidden;
    public final boolean temperaturesAuxMinSetpointReadonly;
    public final boolean temperaturesAuxMinSetpointHidden;
    public final boolean temperaturesAuxMaxSetpointReadonly;
    public final boolean temperaturesAuxMaxSetpointHidden;
    public final boolean masterThermostatChannelNoReadonly;
    public final boolean masterThermostatChannelNoHidden;
    //6
    public final boolean heatOrColdSourceSwitchReadonly;
    public final boolean heatOrColdSourceSwitchHidden;
    public final boolean pumpSwitchReadonly;
    public final boolean pumpSwitchHidden;

    @Override
    public int size() {
        return SIZE;
    }
}
