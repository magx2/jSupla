package pl.grzeslowski.jsupla.protocol.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;
import org.junit.jupiter.api.Test;

class PreconditionsTestSize {
    @Test
    void shouldReturnCollectionWhenSizeIsInBounds() {

        // given
        final Collection<String> expectedCollection = Arrays.asList("1", "2");

        // when
        final Collection<String> collection = Preconditions.size(expectedCollection, 1, 2);

        // then
        assertThat(collection).isEqualTo(expectedCollection);
    }

    @Test
    void shouldReturnArrayWhenSizeIsInBounds() {

        // given
        final String[] expectedArray = new String[] {"1", "2"};

        // when
        final String[] array = Preconditions.size(expectedArray, 1, 2);

        // then
        assertThat(array).isEqualTo(expectedArray);
    }

    @Test
    void shouldReturnNumberWhenSizeIsInBounds() {

        // given
        final int expectedNumber = 1;

        // when
        final int number = Preconditions.size(expectedNumber, 1, 2);

        // then
        assertThat(number).isEqualTo(expectedNumber);
    }

    @Test
    void shouldReturnCharSequenceWhenSizeIsInBounds() {

        // given
        final CharSequence expectedCharSequence = " ";

        // when
        final CharSequence charSequence = Preconditions.size(expectedCharSequence, 1, 2);

        // then
        assertThat(charSequence).isEqualTo(expectedCharSequence);
    }

    @Test
    void shouldReturnByteArrayWhenSizeIsInBounds() {

        // given
        final byte[] expectedByteArray = new byte[] {1};

        // when
        final byte[] bytes = Preconditions.size(expectedByteArray, 1, 2);

        // then
        assertThat(bytes).isEqualTo(expectedByteArray);
    }

    @Test
    void shouldReturnByteArrayWhenSizeIsInLongBounds() {

        // given
        final byte[] expectedByteArray = new byte[] {1};

        // when
        final byte[] bytes = Preconditions.size(expectedByteArray, 1L, 2L);

        // then
        assertThat(bytes).isEqualTo(expectedByteArray);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsCollection() {

        // given
        final Collection<String> expectedCollection = Arrays.asList("1", "2", "3");

        // when & then
        assertThrows(
                IllegalArgumentException.class, () -> Preconditions.size(expectedCollection, 1, 2));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsArray() {

        // given
        final String[] expectedArray = new String[] {"1", "2", "3"};

        // when & then
        assertThrows(IllegalArgumentException.class, () -> Preconditions.size(expectedArray, 1, 2));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsNumber() {

        // given
        final int expectedNumber = 3;

        // when & then
        assertThrows(
                IllegalArgumentException.class, () -> Preconditions.size(expectedNumber, 1, 2));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsCharSequence() {

        // given
        final CharSequence expectedCharSequence = "123";

        // when & then
        assertThrows(
                IllegalArgumentException.class,
                () -> Preconditions.size(expectedCharSequence, 1, 2));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsByteArray() {

        // given
        final byte[] expectedByteArray = new byte[] {1, 2, 3};

        // when & then
        assertThrows(
                IllegalArgumentException.class, () -> Preconditions.size(expectedByteArray, 1, 2));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSizeIsInLongBoundsByteArray() {

        // given
        final byte[] expectedByteArray = new byte[] {1, 2, 3};

        // when & then
        assertThrows(
                IllegalArgumentException.class,
                () -> Preconditions.size(expectedByteArray, 1L, 2L));
    }
}
