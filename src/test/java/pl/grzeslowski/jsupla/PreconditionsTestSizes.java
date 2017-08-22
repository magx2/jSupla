package pl.grzeslowski.jsupla;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PreconditionsTestSizes {

    @Test
    public void shouldReturnByteIfInRange() throws Exception {

        // given
        int value = 1;

        // when
        final int byteValue = Preconditions.byteSize(value);

        // then
        assertThat(byteValue).isEqualTo(value);
    }

    @Test
    public void shouldReturnUnsignedByteIfInRange() throws Exception {

        // given
        int value = 1;

        // when
        final int byteValue = Preconditions.byteSize(value);

        // then
        assertThat(byteValue).isEqualTo(value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfValueIsBiggerThanByte() throws Exception {

        // given
        int value = Byte.MAX_VALUE + 1;

        // when
        Preconditions.byteSize(value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfValueIsSmallerThanByte() throws Exception {

        // given
        int value = Byte.MIN_VALUE - 1;

        // when
        Preconditions.byteSize(value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfValueIsBiggerThanUnsignedByte() throws Exception {

        // given
        int value = -1;

        // when
        Preconditions.unsignedByteSize(value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfValueIsSmallerThanUnsignedByte() throws Exception {

        // given
        int value = 256;

        // when
        Preconditions.unsignedByteSize(value);
    }
}