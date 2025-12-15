package pl.grzeslowski.jsupla.protocol.api.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrimitiveDecoderImplParametrizedTestForByte {
    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(new byte[] {0}, 0, (byte) 0),
                Arguments.of(new byte[] {127}, 0, Byte.MAX_VALUE),
                Arguments.of(new byte[] {-128}, 0, Byte.MIN_VALUE),
                Arguments.of(new byte[] {-86}, 0, (byte) -86), // 10101010
                Arguments.of(new byte[] {-16}, 0, (byte) -16), // 11110000
                Arguments.of(new byte[] {85}, 0, (byte) 85), // 01010101
                Arguments.of(new byte[] {15}, 0, (byte) 15) // 00001111
                );
    }

    @ParameterizedTest
    @MethodSource("data")
    void shouldReturnProperValueForArrayOfBytes(byte[] intBytes, int offset, byte byteValue) {

        // when
        final byte result = INSTANCE.parseByte(intBytes, offset);

        // then
        assertThat(result).isEqualTo(byteValue);
    }
}
