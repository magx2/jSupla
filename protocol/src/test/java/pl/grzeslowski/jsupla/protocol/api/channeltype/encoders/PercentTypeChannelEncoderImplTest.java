package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.PercentValue;

class PercentTypeChannelEncoderImplTest {
    private final PercentTypeChannelEncoderImpl encoder = new PercentTypeChannelEncoderImpl();

    @Test
    void shouldOffsetValueByTen() {
        byte[] bytes = encoder.encode(new PercentValue(20));
        assertThat(bytes[0]).isEqualTo((byte) 30);
    }
}
