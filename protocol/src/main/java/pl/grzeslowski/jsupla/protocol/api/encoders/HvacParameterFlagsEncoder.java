package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.structs.HvacParameterFlags;

public class HvacParameterFlagsEncoder
        implements pl.grzeslowski.jsupla.protocol.api.encoders.ProtoWithSizeEncoder<
                HvacParameterFlags> {
    public static final HvacParameterFlagsEncoder INSTANCE = new HvacParameterFlagsEncoder();

    @Override
    public byte[] encode(HvacParameterFlags proto, byte[] bytes, int offset) {
        {
            int subIdx = 0;
            bytes[offset] = flag(bytes[offset], proto.mainThermometerChannelNoReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.mainThermometerChannelNoHidden(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.auxThermometerChannelNoReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.auxThermometerChannelNoHidden(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.binarySensorChannelNoReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.binarySensorChannelNoHidden(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.auxThermometerTypeReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.auxThermometerTypeHidden(), subIdx++);
            offset++;
        } // 0
        {
            int subIdx = 0;
            bytes[offset] =
                    flag(
                            bytes[offset],
                            proto.antiFreezeAndOverheatProtectionEnabledReadonly(),
                            subIdx++);
            bytes[offset] =
                    flag(
                            bytes[offset],
                            proto.antiFreezeAndOverheatProtectionEnabledHidden(),
                            subIdx++);
            bytes[offset] = flag(bytes[offset], proto.usedAlgorithmReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.usedAlgorithmHidden(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.minOnTimeSReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.minOnTimeSHidden(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.minOffTimeSReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.minOffTimeSHidden(), subIdx++);
            offset++;
        } // 1
        {
            int subIdx = 0;
            bytes[offset] = flag(bytes[offset], proto.outputValueOnErrorReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.outputValueOnErrorHidden(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.subfunctionReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.subfunctionHidden(), subIdx++);
            bytes[offset] =
                    flag(
                            bytes[offset],
                            proto.temperatureSetpointChangeSwitchesToManualModeReadonly(),
                            subIdx++);
            bytes[offset] =
                    flag(
                            bytes[offset],
                            proto.temperatureSetpointChangeSwitchesToManualModeHidden(),
                            subIdx++);
            bytes[offset] = flag(bytes[offset], proto.auxMinMaxSetpointEnabledReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.auxMinMaxSetpointEnabledHidden(), subIdx++);
            offset++;
        } // 2
        {
            int subIdx = 0;
            bytes[offset] =
                    flag(bytes[offset], proto.useSeparateHeatCoolOutputsReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.useSeparateHeatCoolOutputsHidden(), subIdx++);
            bytes[offset] =
                    flag(bytes[offset], proto.temperaturesFreezeProtectionReadonly(), subIdx++);
            bytes[offset] =
                    flag(bytes[offset], proto.temperaturesFreezeProtectionHidden(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.temperaturesEcoReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.temperaturesEcoHidden(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.temperaturesComfortReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.temperaturesComfortHidden(), subIdx++);
            offset++;
        } // 3
        {
            int subIdx = 0;
            bytes[offset] = flag(bytes[offset], proto.temperaturesBoostReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.temperaturesBoostHidden(), subIdx++);
            bytes[offset] =
                    flag(bytes[offset], proto.temperaturesHeatProtectionReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.temperaturesHeatProtectionHidden(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.temperaturesHisteresisReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.temperaturesHisteresisHidden(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.temperaturesBelowAlarmReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.temperaturesBelowAlarmHidden(), subIdx++);
            offset++;
        } // 4
        {
            int subIdx = 0;
            bytes[offset] = flag(bytes[offset], proto.temperaturesAboveAlarmReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.temperaturesAboveAlarmHidden(), subIdx++);
            bytes[offset] =
                    flag(bytes[offset], proto.temperaturesAuxMinSetpointReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.temperaturesAuxMinSetpointHidden(), subIdx++);
            bytes[offset] =
                    flag(bytes[offset], proto.temperaturesAuxMaxSetpointReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.temperaturesAuxMaxSetpointHidden(), subIdx++);
            bytes[offset] =
                    flag(bytes[offset], proto.masterThermostatChannelNoReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.masterThermostatChannelNoHidden(), subIdx++);
            offset++;
        } // 5
        {
            int subIdx = 0;
            bytes[offset] = flag(bytes[offset], proto.heatOrColdSourceSwitchReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.heatOrColdSourceSwitchHidden(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.pumpSwitchReadonly(), subIdx++);
            bytes[offset] = flag(bytes[offset], proto.pumpSwitchHidden(), subIdx++);
            //            flags[idx] = flag(flags[idx], , subIdx++);
            //            flags[idx] = flag(flags[idx], , subIdx++);
            //            flags[idx] = flag(flags[idx], , subIdx++);
            //            flags[idx] = flag(flags[idx], , subIdx++);
            offset++;
        } // 6
        {
            int subIdx = 0;
            //            flags[idx] = flag(flags[idx], , subIdx++);
            //            flags[idx] = flag(flags[idx], , subIdx++);
            //            flags[idx] = flag(flags[idx], , subIdx++);
            //            flags[idx] = flag(flags[idx], , subIdx++);
            //            flags[idx] = flag(flags[idx], , subIdx++);
            //            flags[idx] = flag(flags[idx], , subIdx++);
            //            flags[idx] = flag(flags[idx], , subIdx++);
            //            flags[idx] = flag(flags[idx], , subIdx++);
            offset++;
        } // 7

        return bytes;
    }

    private byte flag(byte flag, boolean b, int subIdx) {
        if (b) {
            flag |= (1 << subIdx);
        }
        return flag;
    }
}
