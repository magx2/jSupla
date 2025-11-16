package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;

public class ElectricityMeterV2DecoderImplTest {
    private static final double EPSILON = 0.000001;
    private final ElectricityMeterV2DecoderImpl decoder = new ElectricityMeterV2DecoderImpl();
    private final ElectricityMeterTestPayloadBuilder builder =
            new ElectricityMeterTestPayloadBuilder();

    @Test
    public void shouldRespectBalancedValuesProvidedByV2Payload() {
        // given
        BigInteger expectedForward = new BigInteger("987654321");
        BigInteger expectedReverse = new BigInteger("123456789");

        // when
        ElectricityMeterValue value =
                decoder.decode(builder.buildV2(expectedForward, expectedReverse));

        // then
        assertThat(value.getTotalForwardActiveEnergyBalanced()).isEqualTo(expectedForward);
        assertThat(value.getTotalReverseActiveEnergyBalanced()).isEqualTo(expectedReverse);
        assertThat(value.getPhases()).hasSize(3);
    }

    @Test
    public void shouldDecodeCurrencyCostsAndMetaData() {
        // given
        BigInteger forward = BigInteger.valueOf(123);
        BigInteger reverse = BigInteger.valueOf(456);

        // when
        ElectricityMeterValue value = decoder.decode(builder.buildV2(forward, reverse));

        // then
        assertThat(value.getTotalCost()).isEqualByComparingTo(new BigDecimal("123.45"));
        assertThat(value.getPricePerUnit()).isEqualByComparingTo(new BigDecimal("45.678"));
        assertThat(value.getCurrency()).isEqualTo(Currency.getInstance("PLN"));
        assertThat(value.getMeasuredValues()).isEqualTo(3);
        assertThat(value.getPeriod()).isEqualTo(60);
    }

    @Test
    public void shouldMapPhaseMeasurementsFromV2Payload() {
        // when
        ElectricityMeterValue value =
                decoder.decode(builder.buildV2(BigInteger.ONE, BigInteger.TEN));

        // then
        assertThat(value.getPhases()).hasSize(3);
        ElectricityMeterValue.Phase phase0 = value.getPhases().get(0);
        assertThat(phase0.getTotalForwardActiveEnergy()).isEqualTo(BigInteger.valueOf(200_000));
        assertThat(phase0.getTotalReverseActiveEnergy()).isEqualTo(BigInteger.valueOf(400_000));
        assertThat(phase0.getTotalForwardReactiveEnergy()).isEqualTo(BigInteger.valueOf(100_000));
        assertThat(phase0.getTotalReverseReactiveEnergy()).isEqualTo(BigInteger.valueOf(100_000));
        assertThat(phase0.getVoltage()).isCloseTo(230.0, within(EPSILON));
        assertThat(phase0.getCurrent()).isCloseTo(1.0, within(EPSILON));
        assertThat(phase0.getPowerActive()).isCloseTo(1.0, within(EPSILON));
        assertThat(phase0.getPowerReactive()).isCloseTo(4.0, within(EPSILON));
        assertThat(phase0.getPowerApparent()).isCloseTo(7.0, within(EPSILON));
        assertThat(phase0.getPowerFactor()).isCloseTo(0.9, within(EPSILON));
        assertThat(phase0.getPhaseAngle()).isCloseTo(10.0, within(EPSILON));
        assertThat(phase0.getFrequency()).isEqualTo(5_000);

        ElectricityMeterValue.Phase phase1 = value.getPhases().get(1);
        assertThat(phase1.getTotalForwardActiveEnergy()).isEqualTo(BigInteger.valueOf(300_000));
        assertThat(phase1.getTotalReverseActiveEnergy()).isEqualTo(BigInteger.valueOf(200_000));
        assertThat(phase1.getTotalForwardReactiveEnergy()).isEqualTo(BigInteger.valueOf(200_000));
        assertThat(phase1.getTotalReverseReactiveEnergy()).isEqualTo(BigInteger.valueOf(100_000));
        assertThat(phase1.getVoltage()).isCloseTo(231.0, within(EPSILON));
        assertThat(phase1.getCurrent()).isCloseTo(2.0, within(EPSILON));
        assertThat(phase1.getPowerActive()).isCloseTo(2.0, within(EPSILON));
        assertThat(phase1.getPowerReactive()).isCloseTo(5.0, within(EPSILON));
        assertThat(phase1.getPowerApparent()).isCloseTo(8.0, within(EPSILON));
        assertThat(phase1.getPowerFactor()).isCloseTo(0.95, within(EPSILON));
        assertThat(phase1.getPhaseAngle()).isCloseTo(20.0, within(EPSILON));
        assertThat(phase1.getFrequency()).isEqualTo(5_000);

        ElectricityMeterValue.Phase phase2 = value.getPhases().get(2);
        assertThat(phase2.getTotalForwardActiveEnergy()).isEqualTo(BigInteger.valueOf(400_000));
        assertThat(phase2.getTotalReverseActiveEnergy()).isEqualTo(BigInteger.valueOf(100_000));
        assertThat(phase2.getTotalForwardReactiveEnergy()).isEqualTo(BigInteger.valueOf(300_000));
        assertThat(phase2.getTotalReverseReactiveEnergy()).isEqualTo(BigInteger.valueOf(100_000));
        assertThat(phase2.getVoltage()).isCloseTo(232.0, within(EPSILON));
        assertThat(phase2.getCurrent()).isCloseTo(3.0, within(EPSILON));
        assertThat(phase2.getPowerActive()).isCloseTo(3.0, within(EPSILON));
        assertThat(phase2.getPowerReactive()).isCloseTo(6.0, within(EPSILON));
        assertThat(phase2.getPowerApparent()).isCloseTo(9.0, within(EPSILON));
        assertThat(phase2.getPowerFactor()).isCloseTo(0.99, within(EPSILON));
        assertThat(phase2.getPhaseAngle()).isCloseTo(30.0, within(EPSILON));
        assertThat(phase2.getFrequency()).isEqualTo(5_000);
    }
}
