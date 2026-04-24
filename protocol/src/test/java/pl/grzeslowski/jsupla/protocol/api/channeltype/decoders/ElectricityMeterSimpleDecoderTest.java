package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.ElectricMeterPhaseFlag.EM_VALUE_FLAG_PHASE1_ON;
import static pl.grzeslowski.jsupla.protocol.api.ElectricMeterPhaseFlag.EM_VALUE_FLAG_PHASE2_ON;
import static pl.grzeslowski.jsupla.protocol.api.ElectricMeterPhaseFlag.EM_VALUE_FLAG_PHASE3_ON;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterSimpleValue;

class ElectricityMeterSimpleDecoderTest {
    private final ElectricityMeterSimpleDecoder decoder = new ElectricityMeterSimpleDecoder();

    @Test
    void shouldDecodeTotalForwardActiveEnergy() {
        // when
        ElectricityMeterSimpleValue value = decoder.decode(payload(0, 12_345));

        // then
        assertThat(value.totalForwardActiveEnergy()).isEqualByComparingTo(new BigDecimal("123.45"));
    }

    @Test
    void shouldExposeOnlyEnabledPhases() {
        // when
        ElectricityMeterSimpleValue value =
                decoder.decode(
                        payload(
                                EM_VALUE_FLAG_PHASE1_ON.getValue()
                                        | EM_VALUE_FLAG_PHASE3_ON.getValue(),
                                1));

        // then
        assertThat(value.phase1()).isTrue();
        assertThat(value.phase2()).isFalse();
        assertThat(value.phase3()).isTrue();
    }

    @Test
    void shouldExposeAllPhasesWhenAllPhaseFlagsAreSet() {
        // given
        int flags =
                EM_VALUE_FLAG_PHASE1_ON.getValue()
                        | EM_VALUE_FLAG_PHASE2_ON.getValue()
                        | EM_VALUE_FLAG_PHASE3_ON.getValue();

        // when
        ElectricityMeterSimpleValue value = decoder.decode(payload(flags, 1));

        // then
        assertThat(value.phase1()).isTrue();
        assertThat(value.phase2()).isTrue();
        assertThat(value.phase3()).isTrue();
    }

    @Test
    void shouldNotExposePhasesWhenNoPhaseFlagsAreSet() {
        // when
        ElectricityMeterSimpleValue value = decoder.decode(payload(0, 1));

        // then
        assertThat(value.phase1()).isFalse();
        assertThat(value.phase2()).isFalse();
        assertThat(value.phase3()).isFalse();
    }

    private static byte[] payload(int flags, long totalForwardActiveEnergy) {
        return ByteBuffer.allocate(5)
                .order(ByteOrder.LITTLE_ENDIAN)
                .put((byte) flags)
                .putInt((int) totalForwardActiveEnergy)
                .array();
    }
}
