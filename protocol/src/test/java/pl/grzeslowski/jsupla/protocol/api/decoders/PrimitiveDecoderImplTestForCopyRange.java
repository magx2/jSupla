package pl.grzeslowski.jsupla.protocol.api.decoders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import org.junit.jupiter.api.Test;

@SuppressWarnings("ResultOfMethodCallIgnored")
class PrimitiveDecoderImplTestForCopyRange {

    @Test
    void shouldCopyRange() {

        // given
        final byte[] bytes = new byte[] {9, 8, 7, 6, 5, 4, 3, 2, 1};

        // when
        final byte[] copyOfRange = INSTANCE.copyOfRangeByte(bytes, 3, 6);

        // then
        assertThat(copyOfRange).isEqualTo(new byte[] {6, 5, 4});
    }

    @Test
    void shouldThrowNpeWhenOriginalIsNull() {
        assertThrows(NullPointerException.class, () -> INSTANCE.copyOfRangeByte(null, 1, 2));
    }

    @Test
    void shouldThrowArrayIndexOutOfBoundsExceptionWhenFromIsSmallerThan0() {
        assertThrows(
                ArrayIndexOutOfBoundsException.class,
                () -> INSTANCE.copyOfRangeByte(new byte[5], -1, 2));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenFromIsBiggerThanArray() {
        assertThrows(
                IllegalArgumentException.class, () -> INSTANCE.copyOfRangeByte(new byte[5], 6, 7));
    }

    @Test
    void shouldThrowArrayIndexOutOfBoundsExceptionWhenToIsBiggerThanArray() {
        assertThrows(
                IllegalArgumentException.class, () -> INSTANCE.copyOfRangeByte(new byte[5], 0, 7));
    }

    @Test
    void shouldThrowIaeWhenFromIsBiggerThanTo() {
        assertThrows(
                IllegalArgumentException.class, () -> INSTANCE.copyOfRangeByte(new byte[5], 2, 1));
    }
}
