package pl.grzeslowski.jsupla.protocol.api.encoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.LONG_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder.INSTANCE;
import static pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoderTestUtil.removeOffset;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrimitiveDecoderImplParametrizedTestForLong {
    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(new byte[] {0, 0, 0, 0, 0, 0, 0, 0}, 0, 0),
                Arguments.of(new byte[] {5, 0, 0, 0, 0, 0, 0, 0}, 0, 5),
                Arguments.of(
                        new byte[] {
                            -1, -1, -1, -1, -1, -1, -1, 127,
                        },
                        0,
                        Long.MAX_VALUE),
                Arguments.of(new byte[] {0, 0, 0, 0, 0, 0, 0, -128}, 0, Long.MIN_VALUE),
                // 11111111 11111111 11111111 11111111 10101010 11110000 01010101 00001111
                Arguments.of(new byte[] {15, 85, -16, -86, -1, -1, -1, -1}, 0, -1427090161L),
                // 00001111 01010101 11110000 10101010 00000000 00000000 00000000 00000000
                Arguments.of(new byte[] {-86, -16, 85, 15, 0, 0, 0, 0}, 0, 257290410L),
                // 10101010 11110000 01010101 00001111 01010101 00001111 10101010 11110000
                Arguments.of(
                        new byte[] {-16, -86, 15, 85, 15, 85, -16, -86}, 0, -6129305568511284496L),
                // 01101010 11110000 01010101 00001111 01010101 00001111 10101010 11110000
                Arguments.of(
                        new byte[] {-16, -86, 15, 85, 15, 85, -16, 106}, 0, 7705752486770879216L),
                // offset 11111111 11111111 11111111 11111111 10101010 11110000 01010101
                // 00001111
                Arguments.of(
                        new byte[] {1, 2, 3, 4, 15, 85, -16, -86, -1, -1, -1, -1}, 4, -1427090161L),
                // offset 00001111 01010101 11110000 10101010 00000000 00000000 00000000
                // 00000000
                Arguments.of(new byte[] {-5, -6, -7, -86, -16, 85, 15, 0, 0, 0, 0}, 3, 257290410L),
                // offset 10101010 11110000 01010101 00001111 01010101 00001111 10101010
                // 11110000
                Arguments.of(
                        new byte[] {1, 2, 3, -16, -86, 15, 85, 15, 85, -16, -86},
                        3,
                        -6129305568511284496L),
                // offset 01101010 11110000 01010101 00001111 01010101 00001111 10101010
                // 11110000
                Arguments.of(
                        new byte[] {1, 2, 3, -16, -86, 15, 85, 15, 85, -16, 106},
                        3,
                        7705752486770879216L));
    }

    @ParameterizedTest
    @MethodSource("data")
    void shouldReturnProperValueForArrayOfBytes(byte[] longBytes, int offset, long longValue) {

        // when
        final byte[] bytes = new byte[LONG_SIZE + offset];
        final long writeLong = INSTANCE.writeLong(longValue, bytes, offset);

        // then
        assertThat(writeLong).isEqualTo(LONG_SIZE);
        assertThat(removeOffset(bytes, offset)).isEqualTo(removeOffset(longBytes, offset));
    }
}
