package pl.grzeslowski.jsupla.protocol.api.decoders;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.structs.HvacParameterFlags;

public class HvacParameterFlagsDecoder implements pl.grzeslowski.jsupla.protocol.api.decoders.ProtoWithSizeDecoder<HvacParameterFlags> {
    public static final HvacParameterFlagsDecoder INSTANCE = new HvacParameterFlagsDecoder();

    @SuppressWarnings("UnusedAssignment")
    @Override
    public HvacParameterFlags decode(byte[] bytes, int offset) {
        val flags = PrimitiveDecoder.INSTANCE.copyOfRangeByte(bytes, offset, offset + HvacParameterFlags.SIZE);

        val builder = HvacParameterFlags.builder();
        int idx = 0;
        {
            int subIdx = 0;
            val flagByte = flags[idx];
            builder.mainThermometerChannelNoReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.mainThermometerChannelNoHidden((flagByte & (1 << subIdx++)) != 0);
            builder.auxThermometerChannelNoReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.auxThermometerChannelNoHidden((flagByte & (1 << subIdx++)) != 0);
            builder.binarySensorChannelNoReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.binarySensorChannelNoHidden((flagByte & (1 << subIdx++)) != 0);
            builder.auxThermometerTypeReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.auxThermometerTypeHidden((flagByte & (1 << subIdx++)) != 0);
            idx++;
        }// 0
        {
            int subIdx = 0;
            val flagByte = flags[idx];
            builder.antiFreezeAndOverheatProtectionEnabledReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.antiFreezeAndOverheatProtectionEnabledHidden((flagByte & (1 << subIdx++)) != 0);
            builder.usedAlgorithmReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.usedAlgorithmHidden((flagByte & (1 << subIdx++)) != 0);
            builder.minOnTimeSReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.minOnTimeSHidden((flagByte & (1 << subIdx++)) != 0);
            builder.minOffTimeSReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.minOffTimeSHidden((flagByte & (1 << subIdx++)) != 0);
            idx++;
        }// 1
        {
            int subIdx = 0;
            val flagByte = flags[idx];
            builder.outputValueOnErrorReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.outputValueOnErrorHidden((flagByte & (1 << subIdx++)) != 0);
            builder.subfunctionReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.subfunctionHidden((flagByte & (1 << subIdx++)) != 0);
            builder.temperatureSetpointChangeSwitchesToManualModeReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.temperatureSetpointChangeSwitchesToManualModeHidden((flagByte & (1 << subIdx++)) != 0);
            builder.auxMinMaxSetpointEnabledReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.auxMinMaxSetpointEnabledHidden((flagByte & (1 << subIdx++)) != 0);
            idx++;
        }// 2
        {
            int subIdx = 0;
            val flagByte = flags[idx];
            builder.useSeparateHeatCoolOutputsReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.useSeparateHeatCoolOutputsHidden((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesFreezeProtectionReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesFreezeProtectionHidden((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesEcoReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesEcoHidden((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesComfortReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesComfortHidden((flagByte & (1 << subIdx++)) != 0);
            idx++;
        }// 3
        {
            int subIdx = 0;
            val flagByte = flags[idx];
            builder.temperaturesBoostReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesBoostHidden((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesHeatProtectionReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesHeatProtectionHidden((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesHisteresisReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesHisteresisHidden((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesBelowAlarmReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesBelowAlarmHidden((flagByte & (1 << subIdx++)) != 0);
            idx++;
        }// 4
        {
            int subIdx = 0;
            val flagByte = flags[idx];
            builder.temperaturesAboveAlarmReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesAboveAlarmHidden((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesAuxMinSetpointReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesAuxMinSetpointHidden((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesAuxMaxSetpointReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.temperaturesAuxMaxSetpointHidden((flagByte & (1 << subIdx++)) != 0);
            builder.masterThermostatChannelNoReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.masterThermostatChannelNoHidden((flagByte & (1 << subIdx++)) != 0);
            idx++;
        }// 5
        {
            int subIdx = 0;
            val flagByte = flags[idx];
            builder.heatOrColdSourceSwitchReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.heatOrColdSourceSwitchHidden((flagByte & (1 << subIdx++)) != 0);
            builder.pumpSwitchReadonly((flagByte & (1 << subIdx++)) != 0);
            builder.pumpSwitchHidden((flagByte & (1 << subIdx++)) != 0);
//            val  = (flagByte & (1 << subIdx++)) != 0;
//            val  = (flagByte & (1 << subIdx++)) != 0;
//            val  = (flagByte & (1 << subIdx++)) != 0;
//            val  = (flagByte & (1 << subIdx++)) != 0;
            idx++;
        }// 6
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
        }// 7
        return builder.build();
    }
}
