package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.channeltype.value.HvacValue.Mode.HEAT;

import lombok.val;
import org.junit.Test;

public class HVACValueDecoderImplTest {
    HVACValueDecoderImpl decoder = HVACValueDecoderImpl.INSTANCE;

    @Test
    public void auratonTestCase() {
        // given
        val data = new byte[] {0, 2, 18, 7, 0, 0, 1, 0};

        // when
        val decode = decoder.decode(data);

        // then
        assertThat(decode.on()).isFalse();
        assertThat(decode.mode()).isEqualTo(HEAT);
        assertThat(decode.setPointTemperatureHeat()).isEqualTo(18.1);
        assertThat(decode.setPointTemperatureCool()).isNull();

        val flags = decode.flags();
        assertThat(flags).isNotNull();
        assertThat(flags.setPointTempHeatSet()).isTrue();
        assertThat(flags.setPointTempCoolSet()).isFalse();
        assertThat(flags.heating()).isFalse();
        assertThat(flags.cooling()).isFalse();
        assertThat(flags.weeklySchedule()).isFalse();
        assertThat(flags.countdownTimer()).isFalse();
        assertThat(flags.fanEnabled()).isFalse();
        assertThat(flags.thermometerError()).isFalse();
        assertThat(flags.clockError()).isFalse();
        assertThat(flags.forcedOffBySensor()).isFalse();
        assertThat(flags.cool()).isFalse();
        assertThat(flags.weeklyScheduleTemporalOverride()).isFalse();
        assertThat(flags.batteryCoverOpen()).isFalse();
    }

    @Test
    public void shouldReturnNullSetPointsWhenFlagsNotSet() {
        // given
        val data = new byte[] {1, 3, 0, 0, 0, 0, 0, 0};

        // when
        val decode = decoder.decode(data);

        // then
        assertThat(decode.on()).isTrue();
        assertThat(decode.setPointTemperatureHeat()).isNull();
        assertThat(decode.setPointTemperatureCool()).isNull();
    }
}
