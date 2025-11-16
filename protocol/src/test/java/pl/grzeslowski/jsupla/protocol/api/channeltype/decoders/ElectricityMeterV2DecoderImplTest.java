package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;

public class ElectricityMeterV2DecoderImplTest {
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
}
