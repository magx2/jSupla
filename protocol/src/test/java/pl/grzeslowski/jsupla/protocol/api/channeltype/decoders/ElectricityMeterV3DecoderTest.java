package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.EM_PHASE_SEQUENCE_CURRENT;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.EM_PHASE_SEQUENCE_VOLTAGE;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue.PhaseSequence;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue.Sequence;

class ElectricityMeterV3DecoderTest {
    private final ElectricityMeterV3Decoder decoder = new ElectricityMeterV3Decoder();
    private final ElectricityMeterTestPayloadBuilder builder =
            new ElectricityMeterTestPayloadBuilder();

    @Test
    void shouldRespectBalancedValuesProvidedByV3Payload() {
        // given
        BigInteger expectedForward = new BigInteger("987654321");
        BigInteger expectedReverse = new BigInteger("123456789");

        // when
        ElectricityMeterValue value =
                decoder.decode(builder.buildV3(expectedForward, expectedReverse));

        // then
        assertBigDecimal(value.totalForwardActiveEnergyBalanced(), new BigDecimal("9876.54321"));
        assertBigDecimal(value.totalReverseActiveEnergyBalanced(), new BigDecimal("1234.56789"));
        assertThat(value.phase1()).isPresent();
        assertThat(value.phase2()).isPresent();
        assertThat(value.phase3()).isPresent();
    }

    @Test
    void shouldDecodeTopLevelEnergiesFromV3Payload() {
        // when
        ElectricityMeterValue value =
                decoder.decode(builder.buildV3(BigInteger.ONE, BigInteger.TEN));

        // then
        assertBigDecimal(value.totalForwardActiveEnergy(), new BigDecimal("9"));
        assertBigDecimal(value.totalReverseActiveEnergy(), new BigDecimal("7"));
        assertBigDecimal(value.totalForwardReactiveEnergy(), new BigDecimal("6"));
        assertBigDecimal(value.totalReverseReactiveEnergy(), new BigDecimal("3"));
    }

    @Test
    void shouldDecodeCurrencyCostsAndMetaData() {
        // when
        ElectricityMeterValue value =
                decoder.decode(builder.buildV3(BigInteger.valueOf(123), BigInteger.valueOf(456)));

        // then
        assertBigDecimal(value.totalCost(), new BigDecimal("123.45"));
        assertBigDecimal(value.pricePerUnit(), new BigDecimal("4.5678"));
        assertThat(value.currency()).contains(Currency.getInstance("PLN"));
        assertThat(value.measuredValues()).isEqualTo(3);
        assertThat(value.period()).isEqualTo(60);
        assertThat(value.voltagePhaseAngle12())
                .hasValueSatisfying(angle -> assertThat(angle).isEqualByComparingTo("120"));
        assertThat(value.voltagePhaseAngle13())
                .hasValueSatisfying(angle -> assertThat(angle).isEqualByComparingTo("240"));
        assertThat(value.phaseSequence())
                .contains(
                        new PhaseSequence(
                                Sequence.COUNTER_CLOCKWISE_132, Sequence.COUNTER_CLOCKWISE_132));
    }

    @Test
    void shouldDecodeClockwiseVoltageAndCurrentPhaseSequence() {
        // when
        ElectricityMeterValue value =
                decoder.decode(builder.buildV3(BigInteger.ONE, BigInteger.TEN, (short) 0));

        // then
        assertThat(value.phaseSequence())
                .contains(new PhaseSequence(Sequence.CLOCKWISE_123, Sequence.CLOCKWISE_123));
    }

    @Test
    void shouldDecodeVoltageAndCurrentPhaseSequenceIndependently() {
        // when
        ElectricityMeterValue voltageCounterClockwise =
                decoder.decode(
                        builder.buildV3(
                                BigInteger.ONE, BigInteger.TEN, (short) EM_PHASE_SEQUENCE_VOLTAGE));
        ElectricityMeterValue currentCounterClockwise =
                decoder.decode(
                        builder.buildV3(
                                BigInteger.ONE, BigInteger.TEN, (short) EM_PHASE_SEQUENCE_CURRENT));

        // then
        assertThat(voltageCounterClockwise.phaseSequence())
                .contains(
                        new PhaseSequence(Sequence.COUNTER_CLOCKWISE_132, Sequence.CLOCKWISE_123));
        assertThat(currentCounterClockwise.phaseSequence())
                .contains(
                        new PhaseSequence(Sequence.CLOCKWISE_123, Sequence.COUNTER_CLOCKWISE_132));
    }

    @Test
    void shouldMapPhaseMeasurementsFromV3Payload() {
        // when
        ElectricityMeterValue value =
                decoder.decode(builder.buildV3(BigInteger.ONE, BigInteger.TEN));

        // then
        assertThat(value.phase1()).isPresent();
        assertThat(value.phase2()).isPresent();
        assertThat(value.phase3()).isPresent();
        ElectricityMeterValue.Phase phase0 = value.phase1().get();
        assertBigDecimal(phase0.totalForwardActiveEnergy(), new BigDecimal("2"));
        assertBigDecimal(phase0.totalReverseActiveEnergy(), new BigDecimal("4"));
        assertBigDecimal(phase0.totalForwardReactiveEnergy(), BigDecimal.ONE);
        assertBigDecimal(phase0.totalReverseReactiveEnergy(), BigDecimal.ONE);
        assertBigDecimal(phase0.voltage(), new BigDecimal("230"));
        assertBigDecimal(phase0.current(), BigDecimal.ONE);
        assertBigDecimal(phase0.powerActive(), BigDecimal.ONE);
        assertBigDecimal(phase0.powerReactive(), new BigDecimal("4"));
        assertBigDecimal(phase0.powerApparent(), new BigDecimal("7"));
        assertBigDecimal(phase0.powerFactor(), new BigDecimal("0.9"));
        assertBigDecimal(phase0.phaseAngle(), BigDecimal.TEN);
        assertBigDecimal(phase0.frequency(), new BigDecimal("50"));
    }

    private static void assertBigDecimal(BigDecimal actual, BigDecimal expected) {
        assertThat(actual).isEqualByComparingTo(expected);
    }
}
