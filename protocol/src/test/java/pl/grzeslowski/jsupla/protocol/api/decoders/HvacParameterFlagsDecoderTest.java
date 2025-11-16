package pl.grzeslowski.jsupla.protocol.api.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.structs.HvacParameterFlags;

public class HvacParameterFlagsDecoderTest {
    private final HvacParameterFlagsDecoder decoder = HvacParameterFlagsDecoder.INSTANCE;

    @Test
    public void shouldDecodeFlagsFromBytes() {
        // given
        byte[] raw =
                new byte[] {
                    (byte) 0b01010101,
                    (byte) 0b10101010,
                    (byte) 0b11000011,
                    (byte) 0b00001111,
                    (byte) 0b11110000,
                    (byte) 0b11110011,
                    (byte) 0b00001111,
                    (byte) 0b00000000
                };

        // when
        HvacParameterFlags flags = decoder.decode(raw, 0);

        // then
        assertThat(flags.mainThermometerChannelNoReadonly).isTrue();
        assertThat(flags.mainThermometerChannelNoHidden).isFalse();
        assertThat(flags.auxThermometerChannelNoReadonly).isTrue();
        assertThat(flags.auxThermometerChannelNoHidden).isFalse();
        assertThat(flags.binarySensorChannelNoReadonly).isTrue();
        assertThat(flags.binarySensorChannelNoHidden).isFalse();
        assertThat(flags.auxThermometerTypeReadonly).isTrue();
        assertThat(flags.auxThermometerTypeHidden).isFalse();

        assertThat(flags.antiFreezeAndOverheatProtectionEnabledReadonly).isFalse();
        assertThat(flags.antiFreezeAndOverheatProtectionEnabledHidden).isTrue();
        assertThat(flags.usedAlgorithmHidden).isTrue();
        assertThat(flags.minOnTimeSHidden).isTrue();
        assertThat(flags.minOffTimeSHidden).isTrue();

        assertThat(flags.outputValueOnErrorReadonly).isTrue();
        assertThat(flags.outputValueOnErrorHidden).isTrue();
        assertThat(flags.auxMinMaxSetpointEnabledReadonly).isTrue();
        assertThat(flags.auxMinMaxSetpointEnabledHidden).isTrue();

        assertThat(flags.useSeparateHeatCoolOutputsReadonly).isTrue();
        assertThat(flags.useSeparateHeatCoolOutputsHidden).isTrue();
        assertThat(flags.temperaturesFreezeProtectionReadonly).isTrue();
        assertThat(flags.temperaturesFreezeProtectionHidden).isTrue();
        assertThat(flags.temperaturesComfortHidden).isFalse();

        assertThat(flags.temperaturesHisteresisReadonly).isTrue();
        assertThat(flags.temperaturesHisteresisHidden).isTrue();
        assertThat(flags.temperaturesBelowAlarmReadonly).isTrue();
        assertThat(flags.temperaturesBelowAlarmHidden).isTrue();

        assertThat(flags.temperaturesAboveAlarmReadonly).isTrue();
        assertThat(flags.temperaturesAboveAlarmHidden).isTrue();
        assertThat(flags.temperaturesAuxMaxSetpointReadonly).isTrue();
        assertThat(flags.temperaturesAuxMaxSetpointHidden).isTrue();
        assertThat(flags.masterThermostatChannelNoReadonly).isTrue();
        assertThat(flags.masterThermostatChannelNoHidden).isTrue();

        assertThat(flags.heatOrColdSourceSwitchReadonly).isTrue();
        assertThat(flags.heatOrColdSourceSwitchHidden).isTrue();
        assertThat(flags.pumpSwitchReadonly).isTrue();
        assertThat(flags.pumpSwitchHidden).isTrue();
    }
}
