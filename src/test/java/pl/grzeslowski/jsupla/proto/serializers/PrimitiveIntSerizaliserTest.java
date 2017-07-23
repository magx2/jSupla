package pl.grzeslowski.jsupla.proto.serializers;

import org.junit.Test;

import static org.assertj.core.api.Fail.fail;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;

public class PrimitiveIntSerizaliserTest {
    private static final int VALUE_THAT_I_DO_NOT_CARE = 5;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenArrayIsToSmall() {

        // given
        byte[] bytes = new byte[INT_SIZE - 1];

        // when
        PrimitiveSerizaliser.putUnsignedInt(VALUE_THAT_I_DO_NOT_CARE, bytes, 0);

        // then
        fail("Should throw IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenArrayIsToSmallBecauseOfOffset() {

        // given
        int offset = 3;
        byte[] bytes = new byte[offset + INT_SIZE - 1];

        // when
        PrimitiveSerizaliser.putUnsignedInt(VALUE_THAT_I_DO_NOT_CARE, bytes, offset);

        // then
        fail("Should throw IllegalArgumentException");
    }

}
