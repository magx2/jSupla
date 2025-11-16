package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.structs.HvacParameterFlags;

public class HvacParameterFlagsEncoder
        implements pl.grzeslowski.jsupla.protocol.api.encoders.ProtoWithSizeEncoder<
                HvacParameterFlags> {
    public static final HvacParameterFlagsEncoder INSTANCE = new HvacParameterFlagsEncoder();

    @Override
    public byte[] encode(HvacParameterFlags proto) {
        final byte[] flags = new byte[proto.protoSize()];
        int idx = 0;
        {
            int subIdx = 0;
            flags[idx] = flag(flags[idx], proto.mainThermometerChannelNoReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.mainThermometerChannelNoHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.auxThermometerChannelNoReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.auxThermometerChannelNoHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.binarySensorChannelNoReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.binarySensorChannelNoHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.auxThermometerTypeReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.auxThermometerTypeHidden, subIdx++);
            idx++;
        } // 0
        {
            int subIdx = 0;
            flags[idx] =
                    flag(
                            flags[idx],
                            proto.antiFreezeAndOverheatProtectionEnabledReadonly,
                            subIdx++);
            flags[idx] =
                    flag(flags[idx], proto.antiFreezeAndOverheatProtectionEnabledHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.usedAlgorithmReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.usedAlgorithmHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.minOnTimeSReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.minOnTimeSHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.minOffTimeSReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.minOffTimeSHidden, subIdx++);
            idx++;
        } // 1
        {
            int subIdx = 0;
            flags[idx] = flag(flags[idx], proto.outputValueOnErrorReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.outputValueOnErrorHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.subfunctionReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.subfunctionHidden, subIdx++);
            flags[idx] =
                    flag(
                            flags[idx],
                            proto.temperatureSetpointChangeSwitchesToManualModeReadonly,
                            subIdx++);
            flags[idx] =
                    flag(
                            flags[idx],
                            proto.temperatureSetpointChangeSwitchesToManualModeHidden,
                            subIdx++);
            flags[idx] = flag(flags[idx], proto.auxMinMaxSetpointEnabledReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.auxMinMaxSetpointEnabledHidden, subIdx++);
            idx++;
        } // 2
        {
            int subIdx = 0;
            flags[idx] = flag(flags[idx], proto.useSeparateHeatCoolOutputsReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.useSeparateHeatCoolOutputsHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesFreezeProtectionReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesFreezeProtectionHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesEcoReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesEcoHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesComfortReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesComfortHidden, subIdx++);
            idx++;
        } // 3
        {
            int subIdx = 0;
            flags[idx] = flag(flags[idx], proto.temperaturesBoostReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesBoostHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesHeatProtectionReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesHeatProtectionHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesHisteresisReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesHisteresisHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesBelowAlarmReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesBelowAlarmHidden, subIdx++);
            idx++;
        } // 4
        {
            int subIdx = 0;
            flags[idx] = flag(flags[idx], proto.temperaturesAboveAlarmReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesAboveAlarmHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesAuxMinSetpointReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesAuxMinSetpointHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesAuxMaxSetpointReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.temperaturesAuxMaxSetpointHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.masterThermostatChannelNoReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.masterThermostatChannelNoHidden, subIdx++);
            idx++;
        } // 5
        {
            int subIdx = 0;
            flags[idx] = flag(flags[idx], proto.heatOrColdSourceSwitchReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.heatOrColdSourceSwitchHidden, subIdx++);
            flags[idx] = flag(flags[idx], proto.pumpSwitchReadonly, subIdx++);
            flags[idx] = flag(flags[idx], proto.pumpSwitchHidden, subIdx++);
            //            flags[idx] = flag(flags[idx], , subIdx++);
            //            flags[idx] = flag(flags[idx], , subIdx++);
            //            flags[idx] = flag(flags[idx], , subIdx++);
            //            flags[idx] = flag(flags[idx], , subIdx++);
            idx++;
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
            idx++;
        } // 7

        return flags;
    }

    private byte flag(byte flag, boolean b, int subIdx) {
        if (b) {
            flag |= (1 << subIdx);
        }
        return flag;
    }
}
