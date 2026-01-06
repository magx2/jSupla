package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static java.math.RoundingMode.HALF_EVEN;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.HvacFlag.*;
import static pl.grzeslowski.jsupla.protocol.api.HvacMode.SUPLA_HVAC_MODE_HEAT;

import java.math.BigDecimal;
import lombok.val;
import org.junit.jupiter.api.Test;

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
        assertThat(decode.setPointTemperatureHeat())
                .isEqualTo(BigDecimal.valueOf(18.10).setScale(2, HALF_EVEN));
        assertThat(decode.setPointTemperatureCool()).isNull();

        val flags = decode.flags();
        assertThat(flags).contains(SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_HEAT_SET);
        assertThat(flags).doesNotContain(SUPLA_HVAC_VALUE_FLAG_SETPOINT_TEMP_COOL_SET);
        assertThat(flags).doesNotContain(SUPLA_HVAC_VALUE_FLAG_HEATING);
        assertThat(flags).doesNotContain(SUPLA_HVAC_VALUE_FLAG_COOLING);
        assertThat(flags).doesNotContain(SUPLA_HVAC_VALUE_FLAG_WEEKLY_SCHEDULE);
        assertThat(flags).doesNotContain(SUPLA_HVAC_VALUE_FLAG_COUNTDOWN_TIMER);
        assertThat(flags).doesNotContain(SUPLA_HVAC_VALUE_FLAG_FAN_ENABLED);
        assertThat(flags).doesNotContain(SUPLA_HVAC_VALUE_FLAG_THERMOMETER_ERROR);
        assertThat(flags).doesNotContain(SUPLA_HVAC_VALUE_FLAG_CLOCK_ERROR);
        assertThat(flags).doesNotContain(SUPLA_HVAC_VALUE_FLAG_FORCED_OFF_BY_SENSOR);
        assertThat(flags).doesNotContain(SUPLA_HVAC_VALUE_FLAG_COOL);
        assertThat(flags).doesNotContain(SUPLA_HVAC_VALUE_FLAG_WEEKLY_SCHEDULE_TEMPORAL_OVERRIDE);
        assertThat(flags).doesNotContain(SUPLA_HVAC_VALUE_FLAG_BATTERY_COVER_OPEN);
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
