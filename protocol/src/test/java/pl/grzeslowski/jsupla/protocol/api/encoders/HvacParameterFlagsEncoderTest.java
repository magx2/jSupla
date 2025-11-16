package pl.grzeslowski.jsupla.protocol.api.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.structs.HvacParameterFlags;

public class HvacParameterFlagsEncoderTest {
    private final HvacParameterFlagsEncoder encoder = HvacParameterFlagsEncoder.INSTANCE;

    @Test
    public void shouldEncodeBooleanFlagsIntoBytes() {
        // given
        HvacParameterFlags flags =
                HvacParameterFlags.builder()
                        .mainThermometerChannelNoReadonly(true)
                        .auxThermometerChannelNoReadonly(true)
                        .binarySensorChannelNoReadonly(true)
                        .auxThermometerTypeReadonly(true)
                        .antiFreezeAndOverheatProtectionEnabledHidden(true)
                        .usedAlgorithmHidden(true)
                        .minOnTimeSHidden(true)
                        .minOffTimeSHidden(true)
                        .outputValueOnErrorReadonly(true)
                        .outputValueOnErrorHidden(true)
                        .auxMinMaxSetpointEnabledReadonly(true)
                        .auxMinMaxSetpointEnabledHidden(true)
                        .useSeparateHeatCoolOutputsReadonly(true)
                        .useSeparateHeatCoolOutputsHidden(true)
                        .temperaturesFreezeProtectionReadonly(true)
                        .temperaturesFreezeProtectionHidden(true)
                        .temperaturesHisteresisReadonly(true)
                        .temperaturesHisteresisHidden(true)
                        .temperaturesBelowAlarmReadonly(true)
                        .temperaturesBelowAlarmHidden(true)
                        .temperaturesAboveAlarmReadonly(true)
                        .temperaturesAboveAlarmHidden(true)
                        .temperaturesAuxMaxSetpointReadonly(true)
                        .temperaturesAuxMaxSetpointHidden(true)
                        .masterThermostatChannelNoReadonly(true)
                        .masterThermostatChannelNoHidden(true)
                        .heatOrColdSourceSwitchReadonly(true)
                        .heatOrColdSourceSwitchHidden(true)
                        .pumpSwitchReadonly(true)
                        .pumpSwitchHidden(true)
                        .build();

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
