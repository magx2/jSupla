package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue;

public class ColorTypeChannelDecoderImplTest {
    private final ColorTypeChannelDecoderImpl decoder = new ColorTypeChannelDecoderImpl();

    @Test
    public void shouldDecodeAllComponents() {
        // given
        byte[] payload = new byte[] {10, 20, 30, 40, 50};

        // when
        RgbValue value = decoder.decode(payload);

        // then
        assertThat(value.getBrightness()).isEqualTo((short) 10);
        assertThat(value.getColorBrightness()).isEqualTo((short) 20);
        assertThat(value.getRed()).isEqualTo((short) 30);
        assertThat(value.getGreen()).isEqualTo((short) 40);
        assertThat(value.getBlue()).isEqualTo((short) 50);
    }
}
