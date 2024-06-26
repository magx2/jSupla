package pl.grzeslowski.jsupla.protocol.api.decoders;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class PrimitiveDecoderImplTestForCopyRange {

    @Test
    public void shouldCopyRange() throws Exception {

        // given
        final byte[] bytes = new byte[]{9, 8, 7, 6, 5, 4, 3, 2, 1};

        // when
        final byte[] copyOfRange = INSTANCE.copyOfRangeByte(bytes, 3, 6);

        // then
        assertThat(copyOfRange).isEqualTo(new byte[]{6, 5, 4});
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenOriginalIsNull() throws Exception {
        INSTANCE.copyOfRangeByte(null, 1, 2);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void shouldThrowArrayIndexOutOfBoundsExceptionWhenFromIsSmallerThan0() throws Exception {
        INSTANCE.copyOfRangeByte(new byte[5], -1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenFromIsBiggerThanArray() throws Exception {
        INSTANCE.copyOfRangeByte(new byte[5], 6, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowArrayIndexOutOfBoundsExceptionWhenToIsBiggerThanArray() throws Exception {
        INSTANCE.copyOfRangeByte(new byte[5], 0, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIaeWhenFromIsBiggerThanTo() throws Exception {
        INSTANCE.copyOfRangeByte(new byte[5], 2, 1);
    }
}