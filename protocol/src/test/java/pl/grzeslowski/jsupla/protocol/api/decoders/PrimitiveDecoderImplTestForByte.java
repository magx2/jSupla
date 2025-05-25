package pl.grzeslowski.jsupla.protocol.api.decoders;

import org.junit.jupiter.api.Test;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;

@SuppressWarnings({"PointlessArithmeticExpression", "ResultOfMethodCallIgnored"})
public class PrimitiveDecoderImplTestForByte {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShort() throws Exception {

        // given
        final byte[] bytes = new byte[BYTE_SIZE - 1];

        // when
        PrimitiveDecoder.INSTANCE.parseByte(bytes, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShortWithOffset() throws Exception {

        // given
        final int offset = 100;
        final byte[] bytes = new byte[BYTE_SIZE - 1 + offset];

        // when
        PrimitiveDecoder.INSTANCE.parseByte(bytes, offset);
    }
}
