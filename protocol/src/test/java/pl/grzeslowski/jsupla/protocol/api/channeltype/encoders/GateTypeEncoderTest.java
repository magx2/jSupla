package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.GateValue;

class GateTypeEncoderTest {
    private final GateTypeEncoder encoder = new GateTypeEncoder();

    @Test
    void shouldEncodeOpenAndCloseStates() {
        assertThat(encoder.encode(GateValue.OPEN)[0]).isEqualTo((byte) 1);
        assertThat(encoder.encode(GateValue.CLOSE)[0]).isEqualTo((byte) 0);
    }
}
