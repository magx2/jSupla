package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.PercentValue;

class PercentTypeEncoderTest {
    private final PercentTypeEncoder encoder = new PercentTypeEncoder();

    @ParameterizedTest(name = "{index}: should parse PercentageValue({0}) to byte {1}")
    @MethodSource
    void shouldEncode(int percentage, byte expectedByte) {
        // given
        var value = new PercentValue(percentage);

        // when
        var bytes = encoder.encode(value);

        // then
        assertThat(bytes[0]).isEqualTo(expectedByte);
    }

    static Stream<Arguments> shouldEncode() {
        return Stream.of(
                Arguments.of(0, (byte) 0),
                Arguments.of(33, (byte) 33),
                Arguments.of(50, (byte) 50),
                Arguments.of(79, (byte) 79),
                Arguments.of(100, (byte) 100));
    }
}
