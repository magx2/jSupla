package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.OnOffValue;

class OnOffValueTypeEncoderTest {
    private final OnOffTypeEncoder encoder = new OnOffTypeEncoder();

    @Test
    void shouldEncodeOnOffStates() {
        assertThat(encoder.encode(OnOffValue.ON)[0]).isEqualTo((byte) 1);
        assertThat(encoder.encode(OnOffValue.OFF)[0]).isEqualTo((byte) 0);
    }
}
