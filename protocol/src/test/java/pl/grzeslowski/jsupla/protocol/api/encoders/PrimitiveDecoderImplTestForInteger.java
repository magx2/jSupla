package pl.grzeslowski.jsupla.protocol.api.encoders;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder.INSTANCE;

import org.junit.jupiter.api.Test;

class PrimitiveDecoderImplTestForInteger {

    @Test
    void shouldThrowIllegalArgumentExceptionIfArrayIsTooShort() {

        // given
        final byte[] bytes = new byte[INT_SIZE - 1];

        // when & then
        assertThrows(IllegalArgumentException.class, () -> INSTANCE.writeInt(1, bytes, 0));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfArrayIsTooShortWithOffset() {

        // given
        final int offset = 100;
        final byte[] bytes = new byte[INT_SIZE - 1 + offset];

        // when & then
        assertThrows(IllegalArgumentException.class, () -> INSTANCE.writeInt(1, bytes, offset));
    }
}
