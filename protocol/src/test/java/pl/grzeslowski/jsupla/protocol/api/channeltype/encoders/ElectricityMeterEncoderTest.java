package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;

public class ElectricityMeterEncoderTest {
    private final ElectricityMeterEncoder encoder = new ElectricityMeterEncoder();

    @Test
    public void shouldFailBecauseImplementationIsNotProvided() {
        ElectricityMeterValue value = ElectricityMeterValue.builder().build();
        assertThatThrownBy(() -> encoder.encode(value))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
