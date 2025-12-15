package pl.grzeslowski.jsupla.protocol.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PreconditionsTestSizes {

    @Test
    void shouldReturnByteIfInRange() {

        // given
        int value = 1;

        // when
        final int byteValue = Preconditions.byteSize(value);

        // then
        assertThat(byteValue).isEqualTo(value);
    }

    @Test
    void shouldReturnUnsignedByteIfInRange() {

        // given
        int value = 1;

        // when
        final int byteValue = Preconditions.unsignedByteSize(value);

        // then
        assertThat(byteValue).isEqualTo(value);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfValueIsBiggerThanByte() {

        // given
        int value = Byte.MAX_VALUE + 1;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> Preconditions.byteSize(value));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfValueIsSmallerThanByte() {

        // given
        int value = Byte.MIN_VALUE - 1;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> Preconditions.byteSize(value));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfValueIsBiggerThanUnsignedByte() {

        // given
        int value = -1;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> Preconditions.unsignedByteSize(value));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfValueIsSmallerThanUnsignedByte() {

        // given
        int value = 256;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> Preconditions.unsignedByteSize(value));
    }
}
