package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue;

class ColorTypeChannelEncoderImplTest {
    private final ColorTypeChannelEncoderImpl encoder = new ColorTypeChannelEncoderImpl();

    @Test
    void shouldWriteColorComponents() {
        RgbValue value =
                new RgbValue(
                        60,
                        70,
                        10,
                        20,
                        30,
                        RgbValue.Command.TOGGLE_RGB,
                        RgbValue.Subject.COLOR_ONOFF);

        byte[] bytes = encoder.encode(value);

        assertThat(bytes)
                .hasSize(SUPLA_CHANNELVALUE_SIZE)
                .containsExactly(
                        (byte) 60,
                        (byte) 70,
                        (byte) 30,
                        (byte) 20,
                        (byte) 10,
                        (byte) 0x2,
                        (byte) 6,
                        (byte) 0);
    }
}
