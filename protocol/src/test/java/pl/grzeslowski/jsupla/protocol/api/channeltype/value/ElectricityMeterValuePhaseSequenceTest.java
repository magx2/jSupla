package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue.Sequence.CLOCKWISE_123;
import static pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue.Sequence.COUNTER_CLOCKWISE_132;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.EM_PHASE_SEQUENCE_CURRENT;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.EM_PHASE_SEQUENCE_VOLTAGE;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue.PhaseSequence;

class ElectricityMeterValuePhaseSequenceTest {
    @Test
    void shouldParseClockwiseSequencesWhenNoBitsAreSet() {
        PhaseSequence sequence = PhaseSequence.fromBitmask((short) 0);

        assertThat(sequence.voltage()).isEqualTo(CLOCKWISE_123);
        assertThat(sequence.current()).isEqualTo(CLOCKWISE_123);
    }

    @Test
    void shouldParseVoltageAndCurrentBitsIndependently() {
        PhaseSequence voltageOnly = PhaseSequence.fromBitmask((short) EM_PHASE_SEQUENCE_VOLTAGE);
        PhaseSequence currentOnly = PhaseSequence.fromBitmask((short) EM_PHASE_SEQUENCE_CURRENT);

        assertThat(voltageOnly.voltage()).isEqualTo(COUNTER_CLOCKWISE_132);
        assertThat(voltageOnly.current()).isEqualTo(CLOCKWISE_123);
        assertThat(currentOnly.voltage()).isEqualTo(CLOCKWISE_123);
        assertThat(currentOnly.current()).isEqualTo(COUNTER_CLOCKWISE_132);
    }

    @Test
    void shouldParseBothCounterClockwiseWhenBothBitsAreSet() {
        short bitmask = EM_PHASE_SEQUENCE_VOLTAGE | EM_PHASE_SEQUENCE_CURRENT;

        PhaseSequence sequence = PhaseSequence.fromBitmask(bitmask);

        assertThat(sequence.voltage()).isEqualTo(COUNTER_CLOCKWISE_132);
        assertThat(sequence.current()).isEqualTo(COUNTER_CLOCKWISE_132);
    }
}
