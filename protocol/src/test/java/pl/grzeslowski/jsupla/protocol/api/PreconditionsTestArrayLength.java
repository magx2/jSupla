package pl.grzeslowski.jsupla.protocol.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PreconditionsTestArrayLength {
    @Test
    void shouldReturnArrayIfLengthIsCorrect() {

        // given
        final String[] expectedStrings = new String[] {"1", "2", "3"};

        // when
        final String[] strings = Preconditions.checkArrayLength(expectedStrings, 3);

        // then
        assertThat(strings).isEqualTo(expectedStrings);
    }

    @Test
    void shouldReturnByteArrayIfLengthIsCorrect() {

        // given
        final byte[] expectedBytes = new byte[] {1, 2, 3};

        // when
        final byte[] bytes = Preconditions.checkArrayLength(expectedBytes, 3);

        // then
        assertThat(bytes).isEqualTo(expectedBytes);
    }

    @Test
    void shouldReturnCharArrayIfLengthIsCorrect() {

        // given
        final char[] expectedChars = new char[] {1, 2, 3};

        // when
        final char[] chars = Preconditions.checkArrayLength(expectedChars, 3);

        // then
        assertThat(chars).isEqualTo(expectedChars);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfLengthIsBiggerArray() {

        // given
        final String[] expectedStrings = new String[] {"1", "2", "3"};

        // when & then
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    final String[] strings = Preconditions.checkArrayLength(expectedStrings, 2);
                    assertThat(strings).isEqualTo(expectedStrings);
                });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfLengthIsBiggerBytes() {

        // given
        final byte[] expectedBytes = new byte[] {1, 2, 3};

        // when & then
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    final byte[] bytes = Preconditions.checkArrayLength(expectedBytes, 2);
                    assertThat(bytes).isEqualTo(expectedBytes);
                });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfLengthIsBiggerChars() {

        // given
        final char[] expectedChars = new char[] {1, 2, 3};

        // when & then
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    final char[] chars = Preconditions.checkArrayLength(expectedChars, 2);
                    assertThat(chars).isEqualTo(expectedChars);
                });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfLengthIsSmallerArray() {

        // given
        final String[] expectedStrings = new String[] {"1", "2", "3"};

        // when & then
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    final String[] strings = Preconditions.checkArrayLength(expectedStrings, 4);
                    assertThat(strings).isEqualTo(expectedStrings);
                });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfLengthIsSmallerBytes() {

        // given
        final byte[] expectedBytes = new byte[] {1, 2, 3};

        // when & then
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    final byte[] bytes = Preconditions.checkArrayLength(expectedBytes, 4);
                    assertThat(bytes).isEqualTo(expectedBytes);
                });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfLengthIsSmallerChars() {

        // given
        final char[] expectedChars = new char[] {1, 2, 3};

        // when & then
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    final char[] chars = Preconditions.checkArrayLength(expectedChars, 4);
                    assertThat(chars).isEqualTo(expectedChars);
                });
    }
}
