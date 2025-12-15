package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue;

class ColorTypeChannelEncoderImplTest {
    private final ColorTypeChannelEncoderImpl encoder = new ColorTypeChannelEncoderImpl();

    @Test
    void shouldWriteColorComponents() {
        RgbValue value = new RgbValue((short) 5, (short) 10, (short) 15, (short) 20, (short) 25);

        byte[] bytes = encoder.encode(value);

        assertThat(bytes)
                .startsWith((byte) 5, (byte) 10, (byte) 15, (byte) 20, (byte) 25)
                .hasSize(8);
    }
}
