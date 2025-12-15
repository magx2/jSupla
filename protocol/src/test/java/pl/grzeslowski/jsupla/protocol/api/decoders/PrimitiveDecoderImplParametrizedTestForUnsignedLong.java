package pl.grzeslowski.jsupla.protocol.api.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.UNSIGNED_INT_MAX;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.UNSIGNED_LONG_MAX;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.math.BigInteger;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrimitiveDecoderImplParametrizedTestForUnsignedLong {
    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(new byte[] {0, 0, 0, 0, 0, 0, 0, 0}, 0, new BigInteger("0")),
                Arguments.of(new byte[] {5, 0, 0, 0, 0, 0, 0, 0}, 0, new BigInteger("5")),
                Arguments.of(
                        new byte[] {0, 0, 0, -128, 0, 0, 0, 0}, 0, new BigInteger("2147483648")),
                Arguments.of(
                        new byte[] {-1, -1, -1, -1, 0, 0, 0, 0},
                        0,
                        new BigInteger(UNSIGNED_INT_MAX + "")),
                Arguments.of(
                        new byte[] {-1, -1, -1, -1, -1, -1, -1, -1},
                        0,
                        UNSIGNED_LONG_MAX), // max value of ulong
                Arguments.of(
                        new byte[] {15, 85, -16, -86, 0, 0, 0, 0},
                        0,
                        new BigInteger("2867877135")), // 10101010 11110000 01010101 00001111
                Arguments.of(
                        new byte[] {-86, -16, 85, 15, 0, 0, 0, 0},
                        0,
                        new BigInteger("257290410")), // 00001111 01010101 11110000 10101010
                Arguments.of(
                        new byte[] {1, 2, 3, 4, 15, 85, -16, -86, 0, 0, 0, 0},
                        4,
                        new BigInteger("2867877135")), // offset 10101010 11110000 01010101 00001111
                Arguments.of(
                        new byte[] {-5, -6, -7, -86, -16, 85, 15, 0, 0, 0, 0},
                        3,
                        new BigInteger("257290410")) // offset 00001111 01010101 11110000 10101010
                );
    }

    @ParameterizedTest
    @MethodSource("data")
    void shouldReturnProperValueForArrayOfBytes(byte[] intBytes, int offset, BigInteger ulong) {

        // when
        BigInteger ulongResult = INSTANCE.parseUnsignedLong(intBytes, offset);

        // then
        assertThat(ulongResult).isEqualTo(ulong);
    }
}
