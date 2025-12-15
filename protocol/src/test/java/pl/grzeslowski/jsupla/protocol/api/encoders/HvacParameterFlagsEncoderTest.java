package pl.grzeslowski.jsupla.protocol.api.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.structs.HvacParameterFlags;

class HvacParameterFlagsEncoderTest {
    private final HvacParameterFlagsEncoder encoder = HvacParameterFlagsEncoder.INSTANCE;

    @Test
    void shouldEncodeBooleanFlagsIntoBytes() {
        // given
        var mainThermometerChannelNoReadonly = true;
        var mainThermometerChannelNoHidden = false;
        var auxThermometerChannelNoReadonly = true;
        var auxThermometerChannelNoHidden = false;
        var binarySensorChannelNoReadonly = true;
        var binarySensorChannelNoHidden = false;
        var auxThermometerTypeReadonly = true;
        var auxThermometerTypeHidden = false;
        // 1
        var antiFreezeAndOverheatProtectionEnabledReadonly = false;
        var antiFreezeAndOverheatProtectionEnabledHidden = true;
        var usedAlgorithmReadonly = false;
        var usedAlgorithmHidden = true;
        var minOnTimeSReadonly = false;
        var minOnTimeSHidden = true;
        var minOffTimeSReadonly = false;
        var minOffTimeSHidden = true;
        // 2
        var outputValueOnErrorReadonly = true;
        var outputValueOnErrorHidden = true;
        var subfunctionReadonly = false;
        var subfunctionHidden = false;
        var temperatureSetpointChangeSwitchesToManualModeReadonly = false;
        var temperatureSetpointChangeSwitchesToManualModeHidden = false;
        var auxMinMaxSetpointEnabledReadonly = true;
        var auxMinMaxSetpointEnabledHidden = true;
        // 3
        var useSeparateHeatCoolOutputsReadonly = true;
        var useSeparateHeatCoolOutputsHidden = true;
        var temperaturesFreezeProtectionReadonly = true;
        var temperaturesFreezeProtectionHidden = true;
        var temperaturesEcoReadonly = false;
        var temperaturesEcoHidden = false;
        var temperaturesComfortReadonly = false;
        var temperaturesComfortHidden = false;
        // 4
        var temperaturesBoostReadonly = false;
        var temperaturesBoostHidden = false;
        var temperaturesHeatProtectionReadonly = false;
        var temperaturesHeatProtectionHidden = false;
        var temperaturesHisteresisReadonly = true;
        var temperaturesHisteresisHidden = true;
        var temperaturesBelowAlarmReadonly = true;
        var temperaturesBelowAlarmHidden = true;
        // 5
        var temperaturesAboveAlarmReadonly = true;
        var temperaturesAboveAlarmHidden = true;
        var temperaturesAuxMinSetpointReadonly = false;
        var temperaturesAuxMinSetpointHidden = false;
        var temperaturesAuxMaxSetpointReadonly = true;
        var temperaturesAuxMaxSetpointHidden = true;
        var masterThermostatChannelNoReadonly = true;
        var masterThermostatChannelNoHidden = true;
        // 6
        var heatOrColdSourceSwitchReadonly = true;
        var heatOrColdSourceSwitchHidden = true;
        var pumpSwitchReadonly = true;
        var pumpSwitchHidden = true;

        var flags =
                new HvacParameterFlags(
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

        // when
        byte[] encoded = encoder.encode(flags);

        // then
        assertThat(encoded).hasSize(HvacParameterFlags.SIZE);
        assertThat(encoded)
                .containsExactly(
                        (byte) 0b01010101,
                        (byte) 0b10101010,
                        (byte) 0b11000011,
                        (byte) 0b00001111,
                        (byte) 0b11110000,
                        (byte) 0b11110011,
                        (byte) 0b00001111,
                        (byte) 0b00000000);
    }
}
