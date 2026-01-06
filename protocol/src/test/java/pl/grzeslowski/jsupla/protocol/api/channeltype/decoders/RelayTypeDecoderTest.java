package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.OnOffValue;

class RelayTypeDecoderTest {
    private final RelayTypeDecoder decoder = new RelayTypeDecoder();

    @Test
    void shouldDecodeOnValue() {
        assertThat(decoder.decode(new byte[] {1})).isEqualTo(OnOffValue.ON);
    }

    @Test
    void shouldDecodeOffValue() {
        assertThat(decoder.decode(new byte[] {0})).isEqualTo(OnOffValue.OFF);
    }

    @Test
    void shouldFailForUnknownValue() {
        assertThatThrownBy(() -> decoder.decode(new byte[] {3}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Don't know how to map value 3");
    }
}
