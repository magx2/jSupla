package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue;

class ColorTypeChannelDecoderImplTest {
    private final ColorTypeChannelDecoderImpl decoder = new ColorTypeChannelDecoderImpl();

    @Test
    void shouldDecodeAllComponents() {
        // given
        byte[] payload = new byte[] {10, 20, 30, 40, 50};

        // when
        RgbValue value = decoder.decode(payload);

        // then
        assertThat(value.brightness()).isEqualTo((short) 10);
        assertThat(value.colorBrightness()).isEqualTo((short) 20);
        assertThat(value.red()).isEqualTo((short) 30);
        assertThat(value.green()).isEqualTo((short) 40);
        assertThat(value.blue()).isEqualTo((short) 50);
    }
}
