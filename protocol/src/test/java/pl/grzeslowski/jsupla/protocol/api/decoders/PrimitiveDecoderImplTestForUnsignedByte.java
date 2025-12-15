package pl.grzeslowski.jsupla.protocol.api.decoders;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;

import org.junit.jupiter.api.Test;

@SuppressWarnings({"PointlessArithmeticExpression", "ResultOfMethodCallIgnored"})
class PrimitiveDecoderImplTestForUnsignedByte {

    @Test
    void shouldThrowIllegalArgumentExceptionIfArrayIsTooShort() {

        // given
        final byte[] bytes = new byte[BYTE_SIZE - 1];

        // when & then
        assertThrows(
                IllegalArgumentException.class,
                () -> PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, 0));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfArrayIsTooShortWithOffset() {

        // given
        final int offset = 100;
        final byte[] bytes = new byte[BYTE_SIZE - 1 + offset];

        // when & then
        assertThrows(
                IllegalArgumentException.class,
                () -> PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset));
    }
}
