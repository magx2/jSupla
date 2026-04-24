package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;

class ElectricityMeterDecoderTest {
    private final ElectricityMeterDecoder decoder = new ElectricityMeterDecoder();
    private final ElectricityMeterTestPayloadBuilder builder =
            new ElectricityMeterTestPayloadBuilder();

    @Test
    void shouldDecodeElectricityMeterExtendedValue() {
        // when
        ElectricityMeterValue value = decoder.decode(builder.buildV1());

        // then
        assertBigDecimal(value.totalCost(), new BigDecimal("123.45"));
        assertBigDecimal(value.pricePerUnit(), new BigDecimal("4.5678"));
        assertThat(value.currency())
                .hasValueSatisfying(
                        currency -> assertThat(currency.getCurrencyCode()).isEqualTo("PLN"));
        assertThat(value.measuredValues()).isEqualTo(3);
        assertThat(value.period()).isEqualTo(60);
        assertBigDecimal(value.voltagePhaseAngle12(), BigDecimal.ZERO);
        assertBigDecimal(value.voltagePhaseAngle13(), BigDecimal.ZERO);
        assertThat(value.phaseSequence()).isEmpty();
        assertBigDecimal(value.totalForwardActiveEnergy(), new BigDecimal("9"));
        assertBigDecimal(value.totalReverseActiveEnergy(), new BigDecimal("7"));
        assertBigDecimal(value.totalForwardReactiveEnergy(), new BigDecimal("6"));
        assertBigDecimal(value.totalReverseReactiveEnergy(), new BigDecimal("3"));
        assertBigDecimal(value.totalForwardActiveEnergyBalanced(), new BigDecimal("2"));
        assertBigDecimal(value.totalReverseActiveEnergyBalanced(), BigDecimal.ZERO);

        assertThat(value.phase1()).isPresent();
        assertThat(value.phase2()).isPresent();
        assertThat(value.phase3()).isPresent();
        ElectricityMeterValue.Phase phase = value.phase1().get();
        assertBigDecimal(phase.totalForwardActiveEnergy(), new BigDecimal("2"));
        assertBigDecimal(phase.totalReverseActiveEnergy(), new BigDecimal("4"));
        assertBigDecimal(phase.voltage(), new BigDecimal("230"));
        assertBigDecimal(phase.current(), BigDecimal.ONE);
        assertBigDecimal(phase.powerActive(), BigDecimal.ONE);
        assertBigDecimal(phase.powerReactive(), new BigDecimal("4"));
        assertBigDecimal(phase.powerApparent(), new BigDecimal("7"));
        assertBigDecimal(phase.powerFactor(), new BigDecimal("0.9"));
        assertBigDecimal(phase.phaseAngle(), BigDecimal.TEN);
        assertBigDecimal(phase.frequency(), new BigDecimal("50"));
    }

    @Test
    void shouldSumTopLevelForwardActiveEnergyFromPhaseTotals() {
        // given
        BigInteger[] phaseTotals =
                new BigInteger[] {
                    new BigInteger("185474023"), new BigInteger("265543"), new BigInteger("1443998")
                };

        // when
        ElectricityMeterValue value = decoder.decode(builder.buildV1(phaseTotals));

        // then
        assertBigDecimal(value.totalForwardActiveEnergy(), new BigDecimal("1871.83564"));
        assertBigDecimal(
                value.totalForwardActiveEnergy(),
                new BigDecimal("1854.74023")
                        .add(new BigDecimal("2.65543"))
                        .add(new BigDecimal("14.43998")));
    }

    @Test
    void shouldComputeBalancedActiveEnergyWhenForwardIsGreaterThanReverse() {
        // when
        ElectricityMeterValue value =
                decoder.decode(
                        builder.buildV1(
                                energies(100_000, 200_000, 300_000),
                                energies(50_000, 100_000, 150_000),
                                energies(0, 0, 0),
                                energies(0, 0, 0)));

        // then
        assertBigDecimal(value.totalForwardActiveEnergyBalanced(), new BigDecimal("3.0"));
        assertBigDecimal(value.totalReverseActiveEnergyBalanced(), BigDecimal.ZERO);
    }

    @Test
    void shouldComputeBalancedActiveEnergyWhenReverseIsGreaterThanForward() {
        // when
        ElectricityMeterValue value =
                decoder.decode(
                        builder.buildV1(
                                energies(50_000, 100_000, 150_000),
                                energies(100_000, 200_000, 300_000),
                                energies(0, 0, 0),
                                energies(0, 0, 0)));

        // then
        assertBigDecimal(value.totalForwardActiveEnergyBalanced(), BigDecimal.ZERO);
        assertBigDecimal(value.totalReverseActiveEnergyBalanced(), new BigDecimal("3.0"));
    }

    @Test
    void shouldComputeZeroBalancedActiveEnergyWhenForwardAndReverseAreEqual() {
        // when
        ElectricityMeterValue value =
                decoder.decode(
                        builder.buildV1(
                                energies(100_000, 100_000, 100_000),
                                energies(50_000, 100_000, 150_000),
                                energies(0, 0, 0),
                                energies(0, 0, 0)));

        // then
        assertBigDecimal(value.totalForwardActiveEnergyBalanced(), BigDecimal.ZERO);
        assertBigDecimal(value.totalReverseActiveEnergyBalanced(), BigDecimal.ZERO);
    }

    @Test
    void shouldIgnoreReactiveEnergyWhenComputingBalancedActiveEnergy() {
        // when
        ElectricityMeterValue value =
                decoder.decode(
                        builder.buildV1(
                                energies(100_000, 200_000, 300_000),
                                energies(50_000, 100_000, 150_000),
                                energies(900_000_000, 800_000_000, 700_000_000),
                                energies(600_000_000, 500_000_000, 400_000_000)));

        // then
        assertBigDecimal(value.totalForwardActiveEnergyBalanced(), new BigDecimal("3.0"));
        assertBigDecimal(value.totalReverseActiveEnergyBalanced(), BigDecimal.ZERO);
    }

    private static BigInteger[] energies(long phase1, long phase2, long phase3) {
        return new BigInteger[] {
            BigInteger.valueOf(phase1), BigInteger.valueOf(phase2), BigInteger.valueOf(phase3)
        };
    }

    private static void assertBigDecimal(BigDecimal actual, BigDecimal expected) {
        assertThat(actual).isEqualByComparingTo(expected);
    }
}
