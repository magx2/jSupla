package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.HvacMode.SUPLA_HVAC_MODE_HEAT;

import lombok.val;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.HvacFlag;

class HvacTypeDecoderTest {
    HvacTypeDecoder decoder = new HvacTypeDecoder();

    @Test
    void auratonTestCase() {
        // given
        val data = new byte[] {0, 2, 18, 7, 0, 0, 1, 0};

        // when
        val decode = decoder.decode(data);

        // then
        assertThat(decode.on()).isFalse();
        assertThat(decode.mode()).isEqualTo(SUPLA_HVAC_MODE_HEAT);
        assertThat(decode.setPointTemperatureHeat()).isEqualTo(18.1);
        assertThat(decode.setPointTemperatureCool()).isNull();

        val flags = decode.flags();
        assertThat(flags).contains(HvacFlag.SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_HEAT_SET);
        assertThat(flags).doesNotContain(HvacFlag.SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_COOL_SET);
        assertThat(flags).doesNotContain(HvacFlag.SUPLA_HVAC_VALUE_FLAG_HEATING);
        assertThat(flags).doesNotContain(HvacFlag.SUPLA_HVAC_VALUE_FLAG_COOLING);
        assertThat(flags).doesNotContain(HvacFlag.SUPLA_HVAC_VALUE_FLAG_WEEKLY_SCHEDULE);
        assertThat(flags).doesNotContain(HvacFlag.SUPLA_HVAC_VALUE_FLAG_COUNTDOWN_TIMER);
        assertThat(flags).doesNotContain(HvacFlag.SUPLA_HVAC_VALUE_FLAG_FAN_ENABLED);
        assertThat(flags).doesNotContain(HvacFlag.SUPLA_HVAC_VALUE_FLAG_THERMOMETER_ERROR);
        assertThat(flags).doesNotContain(HvacFlag.SUPLA_HVAC_VALUE_FLAG_CLOCK_ERROR);
        assertThat(flags).doesNotContain(HvacFlag.SUPLA_HVAC_VALUE_FLAG_FORCED_OFF_BY_SENSOR);
        assertThat(flags).doesNotContain(HvacFlag.SUPLA_HVAC_VALUE_FLAG_COOL);
        assertThat(flags)
                .doesNotContain(HvacFlag.SUPLA_HVAC_VALUE_FLAG_WEEKLY_SCHEDULE_TEMPORAL_OVERRIDE);
        assertThat(flags).doesNotContain(HvacFlag.SUPLA_HVAC_VALUE_FLAG_BATTERY_COVER_OPEN);
    }

    @Test
    void shouldReturnNullSetPointsWhenFlagsNotSet() {
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
