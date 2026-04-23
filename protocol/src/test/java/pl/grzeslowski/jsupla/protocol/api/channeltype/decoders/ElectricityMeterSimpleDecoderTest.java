package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue.Phase.EMPTY;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.EM_VALUE_FLAG_PHASE1_ON;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.EM_VALUE_FLAG_PHASE2_ON;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.EM_VALUE_FLAG_PHASE3_ON;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;

class ElectricityMeterSimpleDecoderTest {
    private final ElectricityMeterSimpleDecoder decoder = new ElectricityMeterSimpleDecoder();

    @Test
    void shouldDecodeTotalForwardActiveEnergy() {
        // when
        ElectricityMeterValue value = decoder.decode(payload(0, 12_345));

        // then
        assertOptionalBigDecimal(
                value.totalForwardActiveEnergyBalanced(), new BigDecimal("123.45"));
        assertThat(value.voltagePhaseAngle12()).isEmpty();
        assertThat(value.voltagePhaseAngle13()).isEmpty();
        assertThat(value.phaseSequence()).isEmpty();
    }

    @Test
    void shouldExposeOnlyEnabledPhases() {
        // when
        ElectricityMeterValue value =
                decoder.decode(payload(EM_VALUE_FLAG_PHASE1_ON | EM_VALUE_FLAG_PHASE3_ON, 1));

        // then
        assertThat(value.phase1()).contains(EMPTY);
        assertThat(value.phase2()).isEmpty();
        assertThat(value.phase3()).contains(EMPTY);
    }

    @Test
    void shouldExposeAllPhasesWhenAllPhaseFlagsAreSet() {
        // given
        int flags = EM_VALUE_FLAG_PHASE1_ON | EM_VALUE_FLAG_PHASE2_ON | EM_VALUE_FLAG_PHASE3_ON;

        // when
        ElectricityMeterValue value = decoder.decode(payload(flags, 1));

        // then
        assertThat(value.phase1()).contains(EMPTY);
        assertThat(value.phase2()).contains(EMPTY);
        assertThat(value.phase3()).contains(EMPTY);
    }

    @Test
    void shouldNotExposePhasesWhenNoPhaseFlagsAreSet() {
        // when
        ElectricityMeterValue value = decoder.decode(payload(0, 1));

        // then
        assertThat(value.phase1()).isEmpty();
        assertThat(value.phase2()).isEmpty();
        assertThat(value.phase3()).isEmpty();
    }

    private static byte[] payload(int flags, long totalForwardActiveEnergy) {
        return ByteBuffer.allocate(5)
                .order(ByteOrder.LITTLE_ENDIAN)
                .put((byte) flags)
                .putInt((int) totalForwardActiveEnergy)
                .array();
    }

    private static void assertOptionalBigDecimal(Optional<BigDecimal> actual, BigDecimal expected) {
        assertThat(actual)
                .hasValueSatisfying(value -> assertThat(value).isEqualByComparingTo(expected));
    }
}
