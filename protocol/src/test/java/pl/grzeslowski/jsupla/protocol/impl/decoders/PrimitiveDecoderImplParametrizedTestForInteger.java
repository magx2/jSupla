package pl.grzeslowski.jsupla.protocol.impl.decoders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

@RunWith(Parameterized.class)
public class PrimitiveDecoderImplParametrizedTestForInteger {
    private final byte[] intBytes;
    private final int offset;
    private final int intValue;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {new byte[]{0, 0, 0, 0}, 0, 0},
            {new byte[]{5, 0, 0, 0}, 0, 5},
            {new byte[]{-1, -1, -1, 127}, 0, Integer.MAX_VALUE},
            {new byte[]{0, 0, 0, -128}, 0, Integer.MIN_VALUE},
            {new byte[]{15, 85, -16, -86}, 0, -1427090161}, // 10101010 11110000 01010101 00001111
            {new byte[]{-86, -16, 85, 15}, 0, 257290410}, // 00001111 01010101 11110000 10101010
            {new byte[]{1, 2, 3, 4, 15, 85, -16, -86}, 4, -1427090161},// offset 10101010 11110000 01010101 00001111
            {new byte[]{-5, -6, -7, -86, -16, 85, 15}, 3, 257290410}, // offset 00001111 01010101 11110000 10101010
        });
    }

    public PrimitiveDecoderImplParametrizedTestForInteger(final byte[] intBytes, final int offset, final int intValue) {
        this.intBytes = intBytes;
        this.offset = offset;
        this.intValue = intValue;
    }

    @Test
    public void shouldReturnProperValueForArrayOfBytes() throws Exception {

        // when
        final int integer = INSTANCE.parseInt(intBytes, offset);

        // then
        assertThat(integer).isEqualTo(intValue);
    }
}