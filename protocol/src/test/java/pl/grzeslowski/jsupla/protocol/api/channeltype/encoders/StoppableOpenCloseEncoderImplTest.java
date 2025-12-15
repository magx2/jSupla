package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.StoppableOpenClose;

class StoppableOpenCloseEncoderImplTest {
    private final StoppableOpenCloseEncoderImpl encoder = new StoppableOpenCloseEncoderImpl();

    @Test
    void shouldEncodeAllStates() {
        assertThat(encoder.encode(StoppableOpenClose.STOP)[0]).isZero();
        assertThat(encoder.encode(StoppableOpenClose.OPEN)[0]).isEqualTo((byte) 1);
        assertThat(encoder.encode(StoppableOpenClose.CLOSE)[0]).isEqualTo((byte) 2);
    }
}
