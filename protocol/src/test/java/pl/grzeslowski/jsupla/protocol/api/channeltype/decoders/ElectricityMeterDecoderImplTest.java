package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;

class ElectricityMeterDecoderImplTest {
    private final ElectricityMeterDecoderImpl decoder = new ElectricityMeterDecoderImpl();
    private final ElectricityMeterTestPayloadBuilder builder =
            new ElectricityMeterTestPayloadBuilder();

    @Test
    void shouldDecodeElectricityMeterExtendedValue() {
        // when
        ElectricityMeterValue value = decoder.decode(builder.buildV1());

        // then
        assertThat(value.totalCost()).isEqualByComparingTo(new BigDecimal("123.45"));
        assertThat(value.pricePerUnit()).isEqualByComparingTo(new BigDecimal("45.678"));
        assertThat(value.currency().getCurrencyCode()).isEqualTo("PLN");
        assertThat(value.measuredValues()).isEqualTo(3);
        assertThat(value.period()).isEqualTo(60);
        assertThat(value.totalForwardActiveEnergyBalanced()).isEqualTo(BigInteger.valueOf(3));
        assertThat(value.totalReverseActiveEnergyBalanced()).isEqualTo(BigInteger.valueOf(4));

        assertThat(value.phases()).hasSize(3);
        ElectricityMeterValue.Phase phase = value.phases().get(0);
        assertThat(phase.totalForwardActiveEnergy()).isEqualTo(BigInteger.valueOf(2));
        assertThat(phase.totalReverseActiveEnergy()).isEqualTo(BigInteger.valueOf(4));
        assertThat(phase.voltage()).isCloseTo(230.00, within(1e-9));
        assertThat(phase.current()).isCloseTo(1.0, within(1e-9));
        assertThat(phase.powerActive()).isCloseTo(1.0, within(1e-9));
        assertThat(phase.powerReactive()).isCloseTo(4.0, within(1e-9));
        assertThat(phase.powerApparent()).isCloseTo(7.0, within(1e-9));
        assertThat(phase.powerFactor()).isCloseTo(0.9, within(1e-9));
        assertThat(phase.phaseAngle()).isCloseTo(10.0, within(1e-9));
        assertThat(phase.frequency()).isEqualTo(5_000);
    }
}
