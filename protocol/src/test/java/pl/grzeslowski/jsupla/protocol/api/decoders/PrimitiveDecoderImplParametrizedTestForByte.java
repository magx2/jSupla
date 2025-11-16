package pl.grzeslowski.jsupla.protocol.api.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class PrimitiveDecoderImplParametrizedTestForByte {
    private final byte[] intBytes;
    private final int offset;
    private final byte byteValue;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][] {
                    {new byte[] {0}, 0, (byte) 0},
                    {new byte[] {127}, 0, Byte.MAX_VALUE},
                    {new byte[] {-128}, 0, Byte.MIN_VALUE},
                    {new byte[] {-86}, 0, (byte) -86}, // 10101010
                    {new byte[] {-16}, 0, (byte) -16}, // 11110000
                    {new byte[] {85}, 0, (byte) 85}, // 01010101
                    {new byte[] {15}, 0, (byte) 15} // 00001111
                });
    }

    public PrimitiveDecoderImplParametrizedTestForByte(
            final byte[] intBytes, final int offset, final byte byteValue) {
        this.intBytes = intBytes;
        this.offset = offset;
        this.byteValue = byteValue;
    }

    @Test
    public void shouldReturnProperValueForArrayOfBytes() throws Exception {

        // when
        final byte byteValue = INSTANCE.parseByte(intBytes, offset);

        // then
        assertThat(byteValue).isEqualTo(this.byteValue);
    }
}
