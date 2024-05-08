package pl.grzeslowski.jsupla.protocol.api.encoders;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder.INSTANCE;

@RunWith(Parameterized.class)
public class PrimitiveDecoderImplParametrizedTestForUnsignedInteger {
    private final byte[] intBytes;
    private final int offset;
    private final long intValue;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {new byte[]{0, 0, 0, 0}, 0, 0},
            {new byte[]{5, 0, 0, 0}, 0, 5},
            {new byte[]{0, 0, 0, -128}, 0, 2147483648L},
            {new byte[]{-1, -1, -1, -1}, 0, 4294967295L},
            {new byte[]{15, 85, -16, -86}, 0, 2867877135L}, // 10101010 11110000 01010101 00001111
            {new byte[]{-86, -16, 85, 15}, 0, 257290410}, // 00001111 01010101 11110000 10101010
            {new byte[]{1, 2, 3, 4, 15, 85, -16, -86}, 4, 2867877135L},// offset 10101010 11110000 01010101 00001111
            {new byte[]{-5, -6, -7, -86, -16, 85, 15}, 3, 257290410}, // offset 00001111 01010101 11110000 10101010
        });
    }

    public PrimitiveDecoderImplParametrizedTestForUnsignedInteger(final byte[] intBytes, final int offset,
                                                                  final long intValue) {
        this.intBytes = intBytes;
        this.offset = offset;
        this.intValue = intValue;
    }

    @Test
    public void shouldReturnProperValueForArrayOfBytes() throws Exception {

        // when
        final byte[] bytes = new byte[INT_SIZE + offset];
        final int writeUnsignedInteger = INSTANCE.writeUnsignedInt(intValue, bytes, offset);

        // then
        assertThat(writeUnsignedInteger).isEqualTo(INT_SIZE);
        Assertions.assertThat(PrimitiveEncoderTestUtil.removeOffset(bytes, offset)).isEqualTo(PrimitiveEncoderTestUtil.removeOffset(intBytes, offset));
    }
}