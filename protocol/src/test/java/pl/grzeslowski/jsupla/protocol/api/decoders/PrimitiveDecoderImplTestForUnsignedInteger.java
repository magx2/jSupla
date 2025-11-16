package pl.grzeslowski.jsupla.protocol.api.decoders;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

import org.junit.Test;

public class PrimitiveDecoderImplTestForUnsignedInteger {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShort() throws Exception {

        // given
        final byte[] bytes = new byte[INT_SIZE - 1];

        // when
        PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShortWithOffset() throws Exception {

        // given
        final int offset = 100;
        final byte[] bytes = new byte[INT_SIZE - 1 + offset];

        // when
        PrimitiveDecoder.INSTANCE.parseUnsignedInt(bytes, offset);
    }
}
