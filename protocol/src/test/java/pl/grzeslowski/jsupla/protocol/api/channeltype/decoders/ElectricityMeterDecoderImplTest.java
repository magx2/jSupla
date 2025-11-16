package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;

public class ElectricityMeterDecoderImplTest {
    private final ElectricityMeterDecoderImpl decoder = new ElectricityMeterDecoderImpl();
    private final ElectricityMeterTestPayloadBuilder builder =
            new ElectricityMeterTestPayloadBuilder();

    @Test
    public void shouldDecodeElectricityMeterExtendedValue() {
        // when
        ElectricityMeterValue value = decoder.decode(builder.buildV1());

        // then
        assertThat(value.getTotalCost()).isEqualByComparingTo(new BigDecimal("123.45"));
        assertThat(value.getPricePerUnit()).isEqualByComparingTo(new BigDecimal("45.678"));
        assertThat(value.getCurrency().getCurrencyCode()).isEqualTo("PLN");
        assertThat(value.getMeasuredValues()).isEqualTo(3);
        assertThat(value.getPeriod()).isEqualTo(60);
        assertThat(value.getTotalForwardActiveEnergyBalanced()).isEqualTo(BigInteger.valueOf(3));
        assertThat(value.getTotalReverseActiveEnergyBalanced()).isEqualTo(BigInteger.valueOf(4));

        assertThat(value.getPhases()).hasSize(3);
        ElectricityMeterValue.Phase phase = value.getPhases().get(0);
        assertThat(phase.getTotalForwardActiveEnergy()).isEqualTo(BigInteger.valueOf(2));
        assertThat(phase.getTotalReverseActiveEnergy()).isEqualTo(BigInteger.valueOf(4));
        assertThat(phase.getVoltage()).isCloseTo(230.00, within(1e-9));
        assertThat(phase.getCurrent()).isCloseTo(1.0, within(1e-9));
        assertThat(phase.getPowerActive()).isCloseTo(1.0, within(1e-9));
        assertThat(phase.getPowerReactive()).isCloseTo(4.0, within(1e-9));
        assertThat(phase.getPowerApparent()).isCloseTo(7.0, within(1e-9));
        assertThat(phase.getPowerFactor()).isCloseTo(0.9, within(1e-9));
        assertThat(phase.getPhaseAngle()).isCloseTo(10.0, within(1e-9));
        assertThat(phase.getFrequency()).isEqualTo(5_000);
    }
}
