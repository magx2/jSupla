package pl.grzeslowski.jsupla.protocol.api.decoders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

@RunWith(Parameterized.class)
public class PrimitiveDecoderImplParametrizedTestForLong {
    private final byte[] longBytes;
    private final int offset;
    private final long longValue;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {new byte[]{0, 0, 0, 0, 0, 0, 0, 0}, 0, 0},
            {new byte[]{5, 0, 0, 0, 0, 0, 0, 0}, 0, 5},
            {new byte[]{-1, -1, -1, -1, -1, -1, -1, 127,}, 0, Long.MAX_VALUE},
            {new byte[]{0, 0, 0, 0, 0, 0, 0, -128}, 0, Long.MIN_VALUE},
            // 11111111 11111111 11111111 11111111 10101010 11110000 01010101 00001111
            {new byte[]{15, 85, -16, -86, -1, -1, -1, -1}, 0, -1427090161L},
            // 00001111 01010101 11110000 10101010 00000000 00000000 00000000 00000000
            {new byte[]{-86, -16, 85, 15, 0, 0, 0, 0}, 0, 257290410L},
            // 10101010 11110000 01010101 00001111 01010101 00001111 10101010 11110000
            {new byte[]{-16, -86, 15, 85, 15, 85, -16, -86}, 0, -6129305568511284496L},
            // 01101010 11110000 01010101 00001111 01010101 00001111 10101010 11110000
            {new byte[]{-16, -86, 15, 85, 15, 85, -16, 106}, 0, 7705752486770879216L},
            // offset 11111111 11111111 11111111 11111111 10101010 11110000 01010101 00001111
            {new byte[]{1, 2, 3, 4, 15, 85, -16, -86, -1, -1, -1, -1}, 4, -1427090161L},
            // offset 00001111 01010101 11110000 10101010 00000000 00000000 00000000 00000000
            {new byte[]{-5, -6, -7, -86, -16, 85, 15, 0, 0, 0, 0}, 3, 257290410L},
            // offset 10101010 11110000 01010101 00001111 01010101 00001111 10101010 11110000
            {new byte[]{1, 2, 3, -16, -86, 15, 85, 15, 85, -16, -86}, 3, -6129305568511284496L},
            // offset 01101010 11110000 01010101 00001111 01010101 00001111 10101010 11110000
            {new byte[]{1, 2, 3, -16, -86, 15, 85, 15, 85, -16, 106}, 3, 7705752486770879216L},
        });
    }

    public PrimitiveDecoderImplParametrizedTestForLong(final byte[] longBytes, final int offset, final long longValue) {
        this.longBytes = longBytes;
        this.offset = offset;
        this.longValue = longValue;
    }

    @Test
    public void shouldReturnProperValueForArrayOfBytes() throws Exception {

        // when
        final long value = INSTANCE.parseLong(longBytes, offset);

        // then
        assertThat(value).isEqualTo(longValue);
    }
}