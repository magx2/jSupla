package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.Test;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

public class PrimitiveDecoderImplTestForUnsignedInteger {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShort() throws Exception {

        // given
        final byte[] bytes = new byte[INT_SIZE - 1];

        // when
        PrimitiveDecoderImpl.INSTANCE.parseUnsignedInt(bytes, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShortWithOffset() throws Exception {

        // given
        final int offset = 100;
        final byte[] bytes = new byte[INT_SIZE - 1 + offset];

        // when
        PrimitiveDecoderImpl.INSTANCE.parseUnsignedInt(bytes, offset);
    }
}
