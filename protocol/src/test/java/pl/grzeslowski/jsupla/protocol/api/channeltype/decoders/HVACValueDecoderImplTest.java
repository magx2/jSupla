package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import lombok.val;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.channeltype.value.HvacValue.Mode.HEAT;

public class HVACValueDecoderImplTest {
    HVACValueDecoderImpl decoder = HVACValueDecoderImpl.INSTANCE;

    @Test
    public void auratonTestCase() {
        // given
        val data = new byte[]{0, 2, 18, 7, 0, 0, 1, 0};

        // when
        val decode = decoder.decode(data);

        // then
        assertThat(decode.isOn()).isFalse();
        assertThat(decode.getMode()).isEqualTo(HEAT);
        assertThat(decode.getSetPointTemperatureHeat()).isEqualTo(18.1);
        assertThat(decode.getSetPointTemperatureCool()).isNull();

        val flags = decode.getFlags();
        assertThat(flags).isNotNull();
        assertThat(flags.isSetPointTempHeatSet()).isTrue();
        assertThat(flags.isSetPointTempCoolSet()).isFalse();
        assertThat(flags.isHeating()).isFalse();
        assertThat(flags.isCooling()).isFalse();
        assertThat(flags.isWeeklySchedule()).isFalse();
        assertThat(flags.isCountdownTimer()).isFalse();
        assertThat(flags.isFanEnabled()).isFalse();
        assertThat(flags.isThermometerError()).isFalse();
        assertThat(flags.isClockError()).isFalse();
        assertThat(flags.isForcedOffBySensor()).isFalse();
        assertThat(flags.isCool()).isFalse();
        assertThat(flags.isWeeklyScheduleTemporalOverride()).isFalse();
        assertThat(flags.isBatteryCoverOpen()).isFalse();
    }
}