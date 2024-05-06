package pl.grzeslowski.jsupla.protocol.impl.encoders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;
import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImplTestUtil.removeOffset;

@RunWith(Parameterized.class)
public class PrimitiveDecoderImplParametrizedTestForUnsignedByte {
    private final byte[] intBytes;
    private final int offset;
    private final short byteValue;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {new byte[]{0}, 0, (short) 0}, // 00000000
            {new byte[]{-1}, 0, (short) 255}, // 11111111
            {new byte[]{-86}, 0, (short) 170}, // 10101010
            {new byte[]{-16}, 0, (short) 240}, // 11110000
            {new byte[]{85}, 0, (short) 85}, // 01010101
            {new byte[]{15}, 0, (short) 15} // 00001111
        });
    }

    public PrimitiveDecoderImplParametrizedTestForUnsignedByte(final byte[] byteBytes,
                                                               final int offset,
                                                               final short byteValue) {
        this.intBytes = byteBytes;
        this.offset = offset;
        this.byteValue = byteValue;
    }

    @Test
    public void shouldReturnProperValueForArrayOfBytes() throws Exception {

        // when
        final byte[] bytes = new byte[BYTE_SIZE + offset];
        final int wroteBytes = INSTANCE.writeUnsignedByte(this.byteValue, bytes, offset);

        // then
        assertThat(wroteBytes).isEqualTo(BYTE_SIZE);
        assertThat(removeOffset(bytes, offset)).isEqualTo(removeOffset(intBytes, offset));
    }
}