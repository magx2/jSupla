package pl.grzeslowski.jsupla.protocol.api.encoders;

import org.junit.Test;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder.INSTANCE;

@SuppressWarnings({"PointlessArithmeticExpression", "ResultOfMethodCallIgnored"})
public class PrimitiveDecoderImplTestForByte {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShort() throws Exception {

        // given
        final byte[] bytes = new byte[BYTE_SIZE - 1];

        // when
        INSTANCE.writeByte((byte) 1, bytes, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShortWithOffset() throws Exception {

        // given
        final int offset = 100;
        final byte[] bytes = new byte[BYTE_SIZE - 1 + offset];

        // when
        INSTANCE.writeByte((byte) 1, bytes, offset);
    }
}
