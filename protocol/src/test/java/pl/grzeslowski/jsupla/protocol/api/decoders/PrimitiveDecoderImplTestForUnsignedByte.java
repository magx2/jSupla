package pl.grzeslowski.jsupla.protocol.api.decoders;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;

import org.junit.Test;

@SuppressWarnings({"PointlessArithmeticExpression", "ResultOfMethodCallIgnored"})
public class PrimitiveDecoderImplTestForUnsignedByte {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShort() throws Exception {

        // given
        final byte[] bytes = new byte[BYTE_SIZE - 1];

        // when
        PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShortWithOffset() throws Exception {

        // given
        final int offset = 100;
        final byte[] bytes = new byte[BYTE_SIZE - 1 + offset];

        // when
        PrimitiveDecoder.INSTANCE.parseUnsignedByte(bytes, offset);
    }
}
