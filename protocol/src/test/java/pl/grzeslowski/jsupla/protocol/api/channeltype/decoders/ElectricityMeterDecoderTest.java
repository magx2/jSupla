package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;
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
        assertOptionalBigDecimal(value.totalCost(), new BigDecimal("123.45"));
        assertOptionalBigDecimal(value.pricePerUnit(), new BigDecimal("4.5678"));
        assertThat(value.currency())
                .hasValueSatisfying(
                        currency -> assertThat(currency.getCurrencyCode()).isEqualTo("PLN"));
        assertThat(value.measuredValues()).isEqualTo(3);
        assertThat(value.period()).contains(60);
        assertThat(value.voltagePhaseAngle12()).isEmpty();
        assertThat(value.voltagePhaseAngle13()).isEmpty();
        assertThat(value.phaseSequence()).isEmpty();
        assertOptionalBigDecimal(value.totalForwardActiveEnergyBalanced(), new BigDecimal("3"));
        assertOptionalBigDecimal(value.totalReverseActiveEnergyBalanced(), new BigDecimal("4"));

        assertThat(value.phase1()).isPresent();
        assertThat(value.phase2()).isPresent();
        assertThat(value.phase3()).isPresent();
        ElectricityMeterValue.Phase phase = value.phase1().get();
        assertOptionalBigDecimal(phase.totalForwardActiveEnergy(), new BigDecimal("2"));
        assertOptionalBigDecimal(phase.totalReverseActiveEnergy(), new BigDecimal("4"));
        assertOptionalBigDecimal(phase.voltage(), new BigDecimal("230"));
        assertOptionalBigDecimal(phase.current(), BigDecimal.ONE);
        assertOptionalBigDecimal(phase.powerActive(), BigDecimal.ONE);
        assertOptionalBigDecimal(phase.powerReactive(), new BigDecimal("4"));
        assertOptionalBigDecimal(phase.powerApparent(), new BigDecimal("7"));
        assertOptionalBigDecimal(phase.powerFactor(), new BigDecimal("0.9"));
        assertOptionalBigDecimal(phase.phaseAngle(), BigDecimal.TEN);
        assertOptionalBigDecimal(phase.frequency(), new BigDecimal("50"));
    }

    private static void assertOptionalBigDecimal(Optional<BigDecimal> actual, BigDecimal expected) {
        assertThat(actual)
                .hasValueSatisfying(value -> assertThat(value).isEqualByComparingTo(expected));
    }
}
