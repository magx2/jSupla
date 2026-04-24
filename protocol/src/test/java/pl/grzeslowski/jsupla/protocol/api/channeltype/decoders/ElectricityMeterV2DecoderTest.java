package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;

class ElectricityMeterV2DecoderTest {
    private final ElectricityMeterV2Decoder decoder = new ElectricityMeterV2Decoder();
    private final ElectricityMeterTestPayloadBuilder builder =
            new ElectricityMeterTestPayloadBuilder();

    @Test
    void shouldRespectBalancedValuesProvidedByV2Payload() {
        // given
        BigInteger expectedForward = new BigInteger("987654321");
        BigInteger expectedReverse = new BigInteger("123456789");

        // when
        ElectricityMeterValue value =
                decoder.decode(builder.buildV2(expectedForward, expectedReverse));

        // then
        assertBigDecimal(value.totalForwardActiveEnergyBalanced(), new BigDecimal("9876.54321"));
        assertBigDecimal(value.totalReverseActiveEnergyBalanced(), new BigDecimal("1234.56789"));
        assertThat(value.phase1()).isPresent();
        assertThat(value.phase2()).isPresent();
        assertThat(value.phase3()).isPresent();
    }

    @Test
    void shouldDecodeTopLevelEnergiesFromV2Payload() {
        // when
        ElectricityMeterValue value =
                decoder.decode(builder.buildV2(BigInteger.ONE, BigInteger.TEN));

        // then
        assertBigDecimal(value.totalForwardActiveEnergy(), new BigDecimal("9"));
        assertBigDecimal(value.totalReverseActiveEnergy(), new BigDecimal("7"));
        assertBigDecimal(value.totalForwardReactiveEnergy(), new BigDecimal("6"));
        assertBigDecimal(value.totalReverseReactiveEnergy(), new BigDecimal("3"));
    }

    @Test
    void shouldDecodeCurrencyCostsAndMetaData() {
        // given
        BigInteger forward = BigInteger.valueOf(123);
        BigInteger reverse = BigInteger.valueOf(456);

        // when
        ElectricityMeterValue value = decoder.decode(builder.buildV2(forward, reverse));

        // then
        assertBigDecimal(value.totalCost(), new BigDecimal("123.45"));
        assertBigDecimal(value.pricePerUnit(), new BigDecimal("4.5678"));
        assertThat(value.currency()).contains(Currency.getInstance("PLN"));
        assertThat(value.measuredValues()).isEqualTo(3);
        assertThat(value.period()).isEqualTo(60);
        assertThat(value.voltagePhaseAngle12()).isEmpty();
        assertThat(value.voltagePhaseAngle13()).isEmpty();
        assertThat(value.phaseSequence()).isEmpty();
    }

    @Test
    void shouldMapPhaseMeasurementsFromV2Payload() {
        // when
        ElectricityMeterValue value =
                decoder.decode(builder.buildV2(BigInteger.ONE, BigInteger.TEN));

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

        ElectricityMeterValue.Phase phase1 = value.phase2().get();
        assertBigDecimal(phase1.totalForwardActiveEnergy(), new BigDecimal("3"));
        assertBigDecimal(phase1.totalReverseActiveEnergy(), new BigDecimal("2"));
        assertBigDecimal(phase1.totalForwardReactiveEnergy(), new BigDecimal("2"));
        assertBigDecimal(phase1.totalReverseReactiveEnergy(), BigDecimal.ONE);
        assertBigDecimal(phase1.voltage(), new BigDecimal("231"));
        assertBigDecimal(phase1.current(), new BigDecimal("2"));
        assertBigDecimal(phase1.powerActive(), new BigDecimal("2"));
        assertBigDecimal(phase1.powerReactive(), new BigDecimal("5"));
        assertBigDecimal(phase1.powerApparent(), new BigDecimal("8"));
        assertBigDecimal(phase1.powerFactor(), new BigDecimal("0.95"));
        assertBigDecimal(phase1.phaseAngle(), new BigDecimal("20"));
        assertBigDecimal(phase1.frequency(), new BigDecimal("50"));

        ElectricityMeterValue.Phase phase2 = value.phase3().get();
        assertBigDecimal(phase2.totalForwardActiveEnergy(), new BigDecimal("4"));
        assertBigDecimal(phase2.totalReverseActiveEnergy(), BigDecimal.ONE);
        assertBigDecimal(phase2.totalForwardReactiveEnergy(), new BigDecimal("3"));
        assertBigDecimal(phase2.totalReverseReactiveEnergy(), BigDecimal.ONE);
        assertBigDecimal(phase2.voltage(), new BigDecimal("232"));
        assertBigDecimal(phase2.current(), new BigDecimal("3"));
        assertBigDecimal(phase2.powerActive(), new BigDecimal("3"));
        assertBigDecimal(phase2.powerReactive(), new BigDecimal("6"));
        assertBigDecimal(phase2.powerApparent(), new BigDecimal("9"));
        assertBigDecimal(phase2.powerFactor(), new BigDecimal("0.99"));
        assertBigDecimal(phase2.phaseAngle(), new BigDecimal("30"));
        assertBigDecimal(phase2.frequency(), new BigDecimal("50"));
    }

    private static void assertBigDecimal(BigDecimal actual, BigDecimal expected) {
        assertThat(actual).isEqualByComparingTo(expected);
    }
}
