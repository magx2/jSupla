package pl.grzeslowski.jsupla.protocol.impl.encoders;

import org.junit.Test;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;

public class PrimitiveDecoderImplTestForUnsignedInteger {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShort() throws Exception {

        // given
        final byte[] bytes = new byte[INT_SIZE - 1];

        // when
        INSTANCE.writeUnsignedInteger(1, bytes, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShortWithOffset() throws Exception {

        // given
        final int offset = 100;
        final byte[] bytes = new byte[INT_SIZE - 1 + offset];

        // when
        INSTANCE.writeUnsignedInteger(1, bytes, offset);
    }
}
