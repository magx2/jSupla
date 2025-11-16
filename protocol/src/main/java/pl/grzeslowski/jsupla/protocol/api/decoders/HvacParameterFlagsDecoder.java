package pl.grzeslowski.jsupla.protocol.api.decoders;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.structs.HvacParameterFlags;

public class HvacParameterFlagsDecoder
        implements pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder<
                HvacParameterFlags> {
    public static final HvacParameterFlagsDecoder INSTANCE = new HvacParameterFlagsDecoder();

    @SuppressWarnings("UnusedAssignment")
    @Override
    public HvacParameterFlags decode(byte[] bytes, int offset) {
        val flags =
                PrimitiveDecoder.INSTANCE.copyOfRangeByte(
                        bytes, offset, offset + HvacParameterFlags.SIZE);

        boolean mainThermometerChannelNoReadonly;
        boolean mainThermometerChannelNoHidden;
        boolean auxThermometerChannelNoReadonly;
        boolean auxThermometerChannelNoHidden;
        boolean binarySensorChannelNoReadonly;
        boolean binarySensorChannelNoHidden;
        boolean auxThermometerTypeReadonly;
        boolean auxThermometerTypeHidden;
        // 1
        boolean antiFreezeAndOverheatProtectionEnabledReadonly;
        boolean antiFreezeAndOverheatProtectionEnabledHidden;
        boolean usedAlgorithmReadonly;
        boolean usedAlgorithmHidden;
        boolean minOnTimeSReadonly;
        boolean minOnTimeSHidden;
        boolean minOffTimeSReadonly;
        boolean minOffTimeSHidden;
        // 2
        boolean outputValueOnErrorReadonly;
        boolean outputValueOnErrorHidden;
        boolean subfunctionReadonly;
        boolean subfunctionHidden;
        boolean temperatureSetpointChangeSwitchesToManualModeReadonly;
        boolean temperatureSetpointChangeSwitchesToManualModeHidden;
        boolean auxMinMaxSetpointEnabledReadonly;
        boolean auxMinMaxSetpointEnabledHidden;
        // 3
        boolean useSeparateHeatCoolOutputsReadonly;
        boolean useSeparateHeatCoolOutputsHidden;
        boolean temperaturesFreezeProtectionReadonly;
        boolean temperaturesFreezeProtectionHidden;
        boolean temperaturesEcoReadonly;
        boolean temperaturesEcoHidden;
        boolean temperaturesComfortReadonly;
        boolean temperaturesComfortHidden;
        // 4
        boolean temperaturesBoostReadonly;
        boolean temperaturesBoostHidden;
        boolean temperaturesHeatProtectionReadonly;
        boolean temperaturesHeatProtectionHidden;
        boolean temperaturesHisteresisReadonly;
        boolean temperaturesHisteresisHidden;
        boolean temperaturesBelowAlarmReadonly;
        boolean temperaturesBelowAlarmHidden;
        // 5
        boolean temperaturesAboveAlarmReadonly;
        boolean temperaturesAboveAlarmHidden;
        boolean temperaturesAuxMinSetpointReadonly;
        boolean temperaturesAuxMinSetpointHidden;
        boolean temperaturesAuxMaxSetpointReadonly;
        boolean temperaturesAuxMaxSetpointHidden;
        boolean masterThermostatChannelNoReadonly;
        boolean masterThermostatChannelNoHidden;
        // 6
        boolean heatOrColdSourceSwitchReadonly;
        boolean heatOrColdSourceSwitchHidden;
        boolean pumpSwitchReadonly;
        boolean pumpSwitchHidden;

        int idx = 0;
        {
            int subIdx = 0;
            val flagByte = flags[idx];
            mainThermometerChannelNoReadonly = ((flagByte & (1 << subIdx++)) != 0);
            mainThermometerChannelNoHidden = ((flagByte & (1 << subIdx++)) != 0);
            auxThermometerChannelNoReadonly = ((flagByte & (1 << subIdx++)) != 0);
            auxThermometerChannelNoHidden = ((flagByte & (1 << subIdx++)) != 0);
            binarySensorChannelNoReadonly = ((flagByte & (1 << subIdx++)) != 0);
            binarySensorChannelNoHidden = ((flagByte & (1 << subIdx++)) != 0);
            auxThermometerTypeReadonly = ((flagByte & (1 << subIdx++)) != 0);
            auxThermometerTypeHidden = ((flagByte & (1 << subIdx++)) != 0);
            idx++;
        } // 0
        {
            int subIdx = 0;
            val flagByte = flags[idx];
            antiFreezeAndOverheatProtectionEnabledReadonly = ((flagByte & (1 << subIdx++)) != 0);
            antiFreezeAndOverheatProtectionEnabledHidden = ((flagByte & (1 << subIdx++)) != 0);
            usedAlgorithmReadonly = ((flagByte & (1 << subIdx++)) != 0);
            usedAlgorithmHidden = ((flagByte & (1 << subIdx++)) != 0);
            minOnTimeSReadonly = ((flagByte & (1 << subIdx++)) != 0);
            minOnTimeSHidden = ((flagByte & (1 << subIdx++)) != 0);
            minOffTimeSReadonly = ((flagByte & (1 << subIdx++)) != 0);
            minOffTimeSHidden = ((flagByte & (1 << subIdx++)) != 0);
            idx++;
        } // 1
        {
            int subIdx = 0;
            val flagByte = flags[idx];
            outputValueOnErrorReadonly = ((flagByte & (1 << subIdx++)) != 0);
            outputValueOnErrorHidden = ((flagByte & (1 << subIdx++)) != 0);
            subfunctionReadonly = ((flagByte & (1 << subIdx++)) != 0);
            subfunctionHidden = ((flagByte & (1 << subIdx++)) != 0);
            temperatureSetpointChangeSwitchesToManualModeReadonly =
                    ((flagByte & (1 << subIdx++)) != 0);
            temperatureSetpointChangeSwitchesToManualModeHidden =
                    ((flagByte & (1 << subIdx++)) != 0);
            auxMinMaxSetpointEnabledReadonly = ((flagByte & (1 << subIdx++)) != 0);
            auxMinMaxSetpointEnabledHidden = ((flagByte & (1 << subIdx++)) != 0);
            idx++;
        } // 2
        {
            int subIdx = 0;
            val flagByte = flags[idx];
            useSeparateHeatCoolOutputsReadonly = ((flagByte & (1 << subIdx++)) != 0);
            useSeparateHeatCoolOutputsHidden = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesFreezeProtectionReadonly = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesFreezeProtectionHidden = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesEcoReadonly = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesEcoHidden = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesComfortReadonly = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesComfortHidden = ((flagByte & (1 << subIdx++)) != 0);
            idx++;
        } // 3
        {
            int subIdx = 0;
            val flagByte = flags[idx];
            temperaturesBoostReadonly = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesBoostHidden = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesHeatProtectionReadonly = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesHeatProtectionHidden = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesHisteresisReadonly = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesHisteresisHidden = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesBelowAlarmReadonly = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesBelowAlarmHidden = ((flagByte & (1 << subIdx++)) != 0);
            idx++;
        } // 4
        {
            int subIdx = 0;
            val flagByte = flags[idx];
            temperaturesAboveAlarmReadonly = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesAboveAlarmHidden = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesAuxMinSetpointReadonly = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesAuxMinSetpointHidden = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesAuxMaxSetpointReadonly = ((flagByte & (1 << subIdx++)) != 0);
            temperaturesAuxMaxSetpointHidden = ((flagByte & (1 << subIdx++)) != 0);
            masterThermostatChannelNoReadonly = ((flagByte & (1 << subIdx++)) != 0);
            masterThermostatChannelNoHidden = ((flagByte & (1 << subIdx++)) != 0);
            idx++;
        } // 5
        {
            int subIdx = 0;
            val flagByte = flags[idx];
            heatOrColdSourceSwitchReadonly = ((flagByte & (1 << subIdx++)) != 0);
            heatOrColdSourceSwitchHidden = ((flagByte & (1 << subIdx++)) != 0);
            pumpSwitchReadonly = ((flagByte & (1 << subIdx++)) != 0);
            pumpSwitchHidden = ((flagByte & (1 << subIdx++)) != 0);
            //            val  = (flagByte & (1 << subIdx++)) != 0;
            //            val  = (flagByte & (1 << subIdx++)) != 0;
            //            val  = (flagByte & (1 << subIdx++)) != 0;
            //            val  = (flagByte & (1 << subIdx++)) != 0;
            idx++;
        } // 6
        {
            //            int subIdx = 0;
            //            val flagByte = flags[idx];
            //            val  = (flagByte & (1 << subIdx++)) != 0;
            //            val  = (flagByte & (1 << subIdx++)) != 0;
            //            val  = (flagByte & (1 << subIdx++)) != 0;
            //            val  = (flagByte & (1 << subIdx++)) != 0;
            //            val  = (flagByte & (1 << subIdx++)) != 0;
            //            val  = (flagByte & (1 << subIdx++)) != 0;
            //            val  = (flagByte & (1 << subIdx++)) != 0;
            //            val  = (flagByte & (1 << subIdx++)) != 0;
            //            idx++;
        } // 7
        return new HvacParameterFlags(
                mainThermometerChannelNoReadonly,
                mainThermometerChannelNoHidden,
                auxThermometerChannelNoReadonly,
                auxThermometerChannelNoHidden,
                binarySensorChannelNoReadonly,
                binarySensorChannelNoHidden,
                auxThermometerTypeReadonly,
                auxThermometerTypeHidden,
                // 1
                antiFreezeAndOverheatProtectionEnabledReadonly,
                antiFreezeAndOverheatProtectionEnabledHidden,
                usedAlgorithmReadonly,
                usedAlgorithmHidden,
                minOnTimeSReadonly,
                minOnTimeSHidden,
                minOffTimeSReadonly,
                minOffTimeSHidden,
                // 2
                outputValueOnErrorReadonly,
                outputValueOnErrorHidden,
                subfunctionReadonly,
                subfunctionHidden,
                temperatureSetpointChangeSwitchesToManualModeReadonly,
                temperatureSetpointChangeSwitchesToManualModeHidden,
                auxMinMaxSetpointEnabledReadonly,
                auxMinMaxSetpointEnabledHidden,
                // 3
                useSeparateHeatCoolOutputsReadonly,
                useSeparateHeatCoolOutputsHidden,
                temperaturesFreezeProtectionReadonly,
                temperaturesFreezeProtectionHidden,
                temperaturesEcoReadonly,
                temperaturesEcoHidden,
                temperaturesComfortReadonly,
                temperaturesComfortHidden,
                // 4
                temperaturesBoostReadonly,
                temperaturesBoostHidden,
                temperaturesHeatProtectionReadonly,
                temperaturesHeatProtectionHidden,
                temperaturesHisteresisReadonly,
                temperaturesHisteresisHidden,
                temperaturesBelowAlarmReadonly,
                temperaturesBelowAlarmHidden,
                // 5
                temperaturesAboveAlarmReadonly,
                temperaturesAboveAlarmHidden,
                temperaturesAuxMinSetpointReadonly,
                temperaturesAuxMinSetpointHidden,
                temperaturesAuxMaxSetpointReadonly,
                temperaturesAuxMaxSetpointHidden,
                masterThermostatChannelNoReadonly,
                masterThermostatChannelNoHidden,
                // 6
                heatOrColdSourceSwitchReadonly,
                heatOrColdSourceSwitchHidden,
                pumpSwitchReadonly,
                pumpSwitchHidden);
    }
}
