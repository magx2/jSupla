package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.OnOff;

class RelayTypeChannelEncoderImplTest {
    private final RelayTypeChannelEncoderImpl encoder = new RelayTypeChannelEncoderImpl();

    @Test
    void shouldEncodeOnOffStates() {
        assertThat(encoder.encode(OnOff.ON)[0]).isEqualTo((byte) 1);
        assertThat(encoder.encode(OnOff.OFF)[0]).isEqualTo((byte) 0);
    }
}
