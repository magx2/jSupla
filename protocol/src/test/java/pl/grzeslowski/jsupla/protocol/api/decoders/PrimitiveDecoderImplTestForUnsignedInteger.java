package pl.grzeslowski.jsupla.protocol.api.decoders;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

import org.junit.jupiter.api.Test;

class PrimitiveDecoderImplTestForUnsignedInteger {

    @Test
    void shouldThrowIllegalArgumentExceptionIfArrayIsTooShort() {

        // given
        final byte[] bytes = new byte[INT_SIZE - 1];

        // when & then
        assertThrows(
                IllegalArgumentException.class,
                () -> PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, 0));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfArrayIsTooShortWithOffset() {

        // given
        final int offset = 100;
        final byte[] bytes = new byte[INT_SIZE - 1 + offset];

        // when & then
        assertThrows(
                IllegalArgumentException.class,
                () -> PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset));
    }
}
