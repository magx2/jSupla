package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.Test;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;

@SuppressWarnings({"PointlessArithmeticExpression", "ResultOfMethodCallIgnored"})
public class PrimitiveDecoderImplTestForByte {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShort() throws Exception {

        // given
        final byte[] bytes = new byte[BYTE_SIZE - 1];

        // when
        PrimitiveDecoderImpl.INSTANCE.parseByte(bytes, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShortWithOffset() throws Exception {

        // given
        final int offset = 100;
        final byte[] bytes = new byte[BYTE_SIZE - 1 + offset];

        // when
        PrimitiveDecoderImpl.INSTANCE.parseByte(bytes, offset);
    }
}
