package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.RgbValue;

class ColorTypeChannelEncoderImplTest {
    private final ColorTypeChannelEncoderImpl encoder = new ColorTypeChannelEncoderImpl();

    @ParameterizedTest
    @MethodSource("subjects")
    void shouldEncodeRgbValue(RgbValue.Subject subject) {
        RgbValue value = new RgbValue(60, 70, 10, 20, 30, 40, RgbValue.Command.TOGGLE_RGB, subject);

        byte[] bytes = encoder.encode(value);

        assertThat(bytes)
                .hasSize(SUPLA_CHANNELVALUE_SIZE)
                .containsExactly(
                        (byte) 60,
                        (byte) 70,
                        (byte) 30,
                        (byte) 20,
                        (byte) 10,
                        (byte) subject.getValue(),
                        (byte) 6,
                        (byte) 40);
    }

    private static Stream<Arguments> subjects() {
        return Stream.of(RgbValue.Subject.values()).map(Arguments::of);
    }
}
