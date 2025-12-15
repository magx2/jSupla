package pl.grzeslowski.jsupla.protocol.api.encoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder.INSTANCE;
import static pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoderTestUtil.removeOffset;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrimitiveDecoderImplParametrizedTestForUnsignedByte {
    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(new byte[] {0}, 0, (short) 0), // 00000000
                Arguments.of(new byte[] {-1}, 0, (short) 255), // 11111111
                Arguments.of(new byte[] {-86}, 0, (short) 170), // 10101010
                Arguments.of(new byte[] {-16}, 0, (short) 240), // 11110000
                Arguments.of(new byte[] {85}, 0, (short) 85), // 01010101
                Arguments.of(new byte[] {15}, 0, (short) 15) // 00001111
                );
    }

    @ParameterizedTest
    @MethodSource("data")
    void shouldReturnProperValueForArrayOfBytes(byte[] intBytes, int offset, short byteValue) {

        // when
        final byte[] bytes = new byte[BYTE_SIZE + offset];
        final int wroteBytes = INSTANCE.writeUnsignedByte(byteValue, bytes, offset);

        // then
        assertThat(wroteBytes).isEqualTo(BYTE_SIZE);
        assertThat(removeOffset(bytes, offset)).isEqualTo(removeOffset(intBytes, offset));
    }
}
