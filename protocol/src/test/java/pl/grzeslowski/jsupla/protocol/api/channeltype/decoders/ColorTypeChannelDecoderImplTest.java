package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue;

class ColorTypeChannelDecoderImplTest {
    private final ColorTypeChannelDecoderImpl decoder = new ColorTypeChannelDecoderImpl();

    @Test
    void shouldDecodeAllComponents() {
        // given
        byte[] payload = new byte[] {99, 100, 90, (byte) 0xFF, (byte) 0x80, 3, 0x3, (byte) 18, 0};

        // when
        RgbValue value = decoder.decode(payload, 1);

        // then
        assertThat(value.brightness()).isEqualTo(100);
        assertThat(value.colorBrightness()).isEqualTo(90);
        assertThat(value.red()).isEqualTo(3);
        assertThat(value.green()).isEqualTo(128);
        assertThat(value.blue()).isEqualTo(255);
        assertThat(value.command()).isEqualTo(RgbValue.Command.STOP_ITERATE_ALL);
        assertThat(value.subject()).isEqualTo(RgbValue.Subject.BOTH_ON_OFF);
    }
}
