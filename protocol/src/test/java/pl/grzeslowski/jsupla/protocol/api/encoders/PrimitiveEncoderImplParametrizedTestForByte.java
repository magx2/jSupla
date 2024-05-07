package pl.grzeslowski.jsupla.protocol.api.encoders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoderImplTestUtil.removeOffset;
import static pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl.INSTANCE;

@RunWith(Parameterized.class)
public class PrimitiveEncoderImplParametrizedTestForByte {
    private final byte[] intBytes;
    private final int offset;
    private final byte byteValue;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {new byte[]{0}, 0, (byte) 0},
            {new byte[]{127}, 0, Byte.MAX_VALUE},
            {new byte[]{-128}, 0, Byte.MIN_VALUE},
            {new byte[]{-86}, 0, (byte) -86}, // 10101010
            {new byte[]{-16}, 0, (byte) -16}, // 11110000
            {new byte[]{85}, 0, (byte) 85}, // 01010101
            {new byte[]{15}, 0, (byte) 15} // 00001111
        });
    }

    public PrimitiveEncoderImplParametrizedTestForByte(final byte[] intBytes, final int offset, final byte byteValue) {
        this.intBytes = intBytes;
        this.offset = offset;
        this.byteValue = byteValue;
    }

    @Test
    public void shouldReturnProperValueForArrayOfBytes() throws Exception {

        // when
        final byte[] bytes = new byte[BYTE_SIZE + offset];
        final int writeByte = INSTANCE.writeByte(byteValue, bytes, offset);

        // then
        assertThat(writeByte).isEqualTo(BYTE_SIZE);
        assertThat(removeOffset(bytes, offset)).isEqualTo(removeOffset(intBytes, offset));
    }
}