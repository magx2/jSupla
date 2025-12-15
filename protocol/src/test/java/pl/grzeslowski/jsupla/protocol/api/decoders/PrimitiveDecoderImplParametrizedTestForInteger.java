package pl.grzeslowski.jsupla.protocol.api.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrimitiveDecoderImplParametrizedTestForInteger {
    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(new byte[] {0, 0, 0, 0}, 0, 0),
                Arguments.of(new byte[] {5, 0, 0, 0}, 0, 5),
                Arguments.of(new byte[] {-1, -1, -1, 127}, 0, Integer.MAX_VALUE),
                Arguments.of(new byte[] {0, 0, 0, -128}, 0, Integer.MIN_VALUE),
                Arguments.of(
                        new byte[] {15, 85, -16, -86},
                        0,
                        -1427090161), // 10101010 11110000 01010101 00001111
                Arguments.of(
                        new byte[] {-86, -16, 85, 15},
                        0,
                        257290410), // 00001111 01010101 11110000 10101010
                Arguments.of(
                        new byte[] {1, 2, 3, 4, 15, 85, -16, -86},
                        4,
                        -1427090161), // offset 10101010 11110000 01010101 00001111
                Arguments.of(
                        new byte[] {-5, -6, -7, -86, -16, 85, 15},
                        3,
                        257290410) // offset 00001111 01010101 11110000 10101010
                );
    }

    @ParameterizedTest
    @MethodSource("data")
    void shouldReturnProperValueForArrayOfBytes(byte[] intBytes, int offset, int intValue) {

        // when
        final int integer = INSTANCE.parseInt(intBytes, offset);

        // then
        assertThat(integer).isEqualTo(intValue);
    }
}
