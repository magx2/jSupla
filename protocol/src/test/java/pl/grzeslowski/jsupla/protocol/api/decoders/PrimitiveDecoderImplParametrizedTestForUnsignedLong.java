package pl.grzeslowski.jsupla.protocol.api.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.UNSIGNED_INT_MAX;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.UNSIGNED_LONG_MAX;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class PrimitiveDecoderImplParametrizedTestForUnsignedLong {
    private final byte[] intBytes;
    private final int offset;
    private final BigInteger ulong;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][] {
                    {new byte[] {0, 0, 0, 0, 0, 0, 0, 0}, 0, new BigInteger("0")},
                    {new byte[] {5, 0, 0, 0, 0, 0, 0, 0}, 0, new BigInteger("5")},
                    {new byte[] {0, 0, 0, -128, 0, 0, 0, 0}, 0, new BigInteger("2147483648")},
                    {
                        new byte[] {-1, -1, -1, -1, 0, 0, 0, 0},
                        0,
                        new BigInteger(UNSIGNED_INT_MAX + "")
                    },
                    {
                        new byte[] {-1, -1, -1, -1, -1, -1, -1, -1}, 0, UNSIGNED_LONG_MAX
                    }, // max value of ulong
                    {
                        new byte[] {15, 85, -16, -86, 0, 0, 0, 0}, 0, new BigInteger("2867877135")
                    }, // 10101010 11110000 01010101 00001111
                    {
                        new byte[] {-86, -16, 85, 15, 0, 0, 0, 0}, 0, new BigInteger("257290410")
                    }, // 00001111 01010101 11110000 10101010
                    {
                        new byte[] {1, 2, 3, 4, 15, 85, -16, -86, 0, 0, 0, 0},
                        4,
                        new BigInteger("2867877135")
                    }, // offset 10101010 11110000 01010101 00001111
                    {
                        new byte[] {-5, -6, -7, -86, -16, 85, 15, 0, 0, 0, 0},
                        3,
                        new BigInteger("257290410")
                    }, // offset 00001111 01010101 11110000 10101010
                });
    }

    public PrimitiveDecoderImplParametrizedTestForUnsignedLong(
            byte[] intBytes, int offset, BigInteger ulong) {
        this.intBytes = intBytes;
        this.offset = offset;
        this.ulong = ulong;
    }

    @Test
    public void shouldReturnProperValueForArrayOfBytes() throws Exception {

        // when
        BigInteger ulong = INSTANCE.parseUnsignedLong(intBytes, offset);

        // then
        assertThat(ulong).isEqualTo(this.ulong);
    }
}
