package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;

class ElectricityMeterV2DecoderTest {
    private static final double EPSILON = 0.000001;
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
        assertThat(value.totalForwardActiveEnergyBalanced()).isEqualTo(expectedForward);
        assertThat(value.totalReverseActiveEnergyBalanced()).isEqualTo(expectedReverse);
        assertThat(value.phases()).hasSize(3);
    }

    @Test
    void shouldDecodeCurrencyCostsAndMetaData() {
        // given
        BigInteger forward = BigInteger.valueOf(123);
        BigInteger reverse = BigInteger.valueOf(456);

        // when
        ElectricityMeterValue value = decoder.decode(builder.buildV2(forward, reverse));

        // then
        assertThat(value.totalCost()).isEqualByComparingTo(new BigDecimal("123.45"));
        assertThat(value.pricePerUnit()).isEqualByComparingTo(new BigDecimal("45.678"));
        assertThat(value.currency()).isEqualTo(Currency.getInstance("PLN"));
        assertThat(value.measuredValues()).isEqualTo(3);
        assertThat(value.period()).isEqualTo(60);
    }

    @Test
    void shouldMapPhaseMeasurementsFromV2Payload() {
        // when
        ElectricityMeterValue value =
                decoder.decode(builder.buildV2(BigInteger.ONE, BigInteger.TEN));

        // then
        assertThat(value.phases()).hasSize(3);
        ElectricityMeterValue.Phase phase0 = value.phases().get(0);
        assertThat(phase0.totalForwardActiveEnergy()).isEqualTo(BigInteger.valueOf(200_000));
        assertThat(phase0.totalReverseActiveEnergy()).isEqualTo(BigInteger.valueOf(400_000));
        assertThat(phase0.totalForwardReactiveEnergy()).isEqualTo(BigInteger.valueOf(100_000));
        assertThat(phase0.totalReverseReactiveEnergy()).isEqualTo(BigInteger.valueOf(100_000));
        assertThat(phase0.voltage()).isCloseTo(230.0, within(EPSILON));
        assertThat(phase0.current()).isCloseTo(1.0, within(EPSILON));
        assertThat(phase0.powerActive()).isCloseTo(1.0, within(EPSILON));
        assertThat(phase0.powerReactive()).isCloseTo(4.0, within(EPSILON));
        assertThat(phase0.powerApparent()).isCloseTo(7.0, within(EPSILON));
        assertThat(phase0.powerFactor()).isCloseTo(0.9, within(EPSILON));
        assertThat(phase0.phaseAngle()).isCloseTo(10.0, within(EPSILON));
        assertThat(phase0.frequency()).isEqualTo(5_000);

        ElectricityMeterValue.Phase phase1 = value.phases().get(1);
        assertThat(phase1.totalForwardActiveEnergy()).isEqualTo(BigInteger.valueOf(300_000));
        assertThat(phase1.totalReverseActiveEnergy()).isEqualTo(BigInteger.valueOf(200_000));
        assertThat(phase1.totalForwardReactiveEnergy()).isEqualTo(BigInteger.valueOf(200_000));
        assertThat(phase1.totalReverseReactiveEnergy()).isEqualTo(BigInteger.valueOf(100_000));
        assertThat(phase1.voltage()).isCloseTo(231.0, within(EPSILON));
        assertThat(phase1.current()).isCloseTo(2.0, within(EPSILON));
        assertThat(phase1.powerActive()).isCloseTo(2.0, within(EPSILON));
        assertThat(phase1.powerReactive()).isCloseTo(5.0, within(EPSILON));
        assertThat(phase1.powerApparent()).isCloseTo(8.0, within(EPSILON));
        assertThat(phase1.powerFactor()).isCloseTo(0.95, within(EPSILON));
        assertThat(phase1.phaseAngle()).isCloseTo(20.0, within(EPSILON));
        assertThat(phase1.frequency()).isEqualTo(5_000);

        ElectricityMeterValue.Phase phase2 = value.phases().get(2);
        assertThat(phase2.totalForwardActiveEnergy()).isEqualTo(BigInteger.valueOf(400_000));
        assertThat(phase2.totalReverseActiveEnergy()).isEqualTo(BigInteger.valueOf(100_000));
        assertThat(phase2.totalForwardReactiveEnergy()).isEqualTo(BigInteger.valueOf(300_000));
        assertThat(phase2.totalReverseReactiveEnergy()).isEqualTo(BigInteger.valueOf(100_000));
        assertThat(phase2.voltage()).isCloseTo(232.0, within(EPSILON));
        assertThat(phase2.current()).isCloseTo(3.0, within(EPSILON));
        assertThat(phase2.powerActive()).isCloseTo(3.0, within(EPSILON));
        assertThat(phase2.powerReactive()).isCloseTo(6.0, within(EPSILON));
        assertThat(phase2.powerApparent()).isCloseTo(9.0, within(EPSILON));
        assertThat(phase2.powerFactor()).isCloseTo(0.99, within(EPSILON));
        assertThat(phase2.phaseAngle()).isCloseTo(30.0, within(EPSILON));
        assertThat(phase2.frequency()).isEqualTo(5_000);
    }
}
