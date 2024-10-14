package pl.grzeslowski.jsupla.protocol.api.decoders;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

public class PrimitiveDecoderImplTestForInteger {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShort() throws Exception {

        // given
        final byte[] bytes = new byte[INT_SIZE - 1];

        // when
        PrimitiveDecoder.INSTANCE.parseInt(bytes, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfArrayIsTooShortWithOffset() throws Exception {

        // given
        final int offset = 100;
        final byte[] bytes = new byte[INT_SIZE - 1 + offset];

        // when
        PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
    }

    @Test
    public void shouldParseUnsignedInt() {
        byte[] uint = {25, 0, 0, 0};
        long parsed = PrimitiveDecoder.INSTANCE.parseUnsignedInt(uint, 0);
        assertThat(parsed).isEqualTo(25L);
    }
}
