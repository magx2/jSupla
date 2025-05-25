package pl.grzeslowski.jsupla.protocol.api;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PreconditionsTestArrayLength {
    @Test
    public void shouldReturnArrayIfLengthIsCorrect() throws Exception {

        // given
        final String[] expectedStrings = new String[]{"1", "2", "3"};

        // when
        final String[] strings = Preconditions.checkArrayLength(expectedStrings, 3);

        // then
        assertThat(strings).isEqualTo(expectedStrings);
    }

    @Test
    public void shouldReturnByteArrayIfLengthIsCorrect() throws Exception {

        // given
        final byte[] expectedBytes = new byte[]{1, 2, 3};

        // when
        final byte[] bytes = Preconditions.checkArrayLength(expectedBytes, 3);

        // then
        assertThat(bytes).isEqualTo(expectedBytes);
    }

    @Test
    public void shouldReturnCharArrayIfLengthIsCorrect() throws Exception {

        // given
        final char[] expectedChars = new char[]{1, 2, 3};

        // when
        final char[] chars = Preconditions.checkArrayLength(expectedChars, 3);

        // then
        assertThat(chars).isEqualTo(expectedChars);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfLengthIsBiggerArray() throws Exception {

        // given
        final String[] expectedStrings = new String[]{"1", "2", "3"};

        // when
        final String[] strings = Preconditions.checkArrayLength(expectedStrings, 2);

        // then
        assertThat(strings).isEqualTo(expectedStrings);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfLengthIsBiggerBytes() throws Exception {

        // given
        final byte[] expectedBytes = new byte[]{1, 2, 3};

        // when
        final byte[] bytes = Preconditions.checkArrayLength(expectedBytes, 2);

        // then
        assertThat(bytes).isEqualTo(expectedBytes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfLengthIsBiggerChars() throws Exception {

        // given
        final char[] expectedChars = new char[]{1, 2, 3};

        // when
        final char[] chars = Preconditions.checkArrayLength(expectedChars, 2);

        // then
        assertThat(chars).isEqualTo(expectedChars);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfLengthIsSmallerArray() throws Exception {

        // given
        final String[] expectedStrings = new String[]{"1", "2", "3"};

        // when
        final String[] strings = Preconditions.checkArrayLength(expectedStrings, 4);

        // then
        assertThat(strings).isEqualTo(expectedStrings);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfLengthIsSmallerBytes() throws Exception {

        // given
        final byte[] expectedBytes = new byte[]{1, 2, 3};

        // when
        final byte[] bytes = Preconditions.checkArrayLength(expectedBytes, 4);

        // then
        assertThat(bytes).isEqualTo(expectedBytes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfLengthIsSmallerChars() throws Exception {

        // given
        final char[] expectedChars = new char[]{1, 2, 3};

        // when
        final char[] chars = Preconditions.checkArrayLength(expectedChars, 4);

        // then
        assertThat(chars).isEqualTo(expectedChars);
    }
}