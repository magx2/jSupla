package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.OnOff;

public class RelayTypeChannelDecoderImplTest {
    private final RelayTypeChannelDecoderImpl decoder = new RelayTypeChannelDecoderImpl();

    @Test
    public void shouldDecodeOnValue() {
        assertThat(decoder.decode(new byte[] {1})).isEqualTo(OnOff.ON);
    }

    @Test
    public void shouldDecodeOffValue() {
        assertThat(decoder.decode(new byte[] {0})).isEqualTo(OnOff.OFF);
    }

    @Test
    public void shouldFailForUnknownValue() {
        assertThatThrownBy(() -> decoder.decode(new byte[] {3}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Don't know how to map value 3");
    }
}
