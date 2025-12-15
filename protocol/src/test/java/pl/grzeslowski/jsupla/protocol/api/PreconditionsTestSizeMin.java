package pl.grzeslowski.jsupla.protocol.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;
import org.junit.jupiter.api.Test;

class PreconditionsTestSizeMin {
    @Test
    void shouldReturnCollectionWhenSizeIsInBounds() {

        // given
        final Collection<String> expectedCollection = Arrays.asList("1", "2");

        // when
        final Collection<String> collection = Preconditions.sizeMin(expectedCollection, 1);

        // then
        assertThat(collection).isEqualTo(expectedCollection);
    }

    @Test
    void shouldReturnArrayWhenSizeIsInBounds() {

        // given
        final String[] expectedArray = new String[] {"1", "2"};

        // when
        final String[] array = Preconditions.sizeMin(expectedArray, 1);

        // then
        assertThat(array).isEqualTo(expectedArray);
    }

    @Test
    void shouldReturnCharSequenceWhenSizeIsInBounds() {

        // given
        final CharSequence expectedCharSequence = " ";

        // when
        final CharSequence charSequence = Preconditions.sizeMin(expectedCharSequence, 1);

        // then
        assertThat(charSequence).isEqualTo(expectedCharSequence);
    }

    @Test
    void shouldReturnByteArrayWhenSizeIsInBounds() {

        // given
        final byte[] expectedByteArray = new byte[] {1};

        // when
        final byte[] bytes = Preconditions.sizeMin(expectedByteArray, 1);

        // then
        assertThat(bytes).isEqualTo(expectedByteArray);
    }

    @Test
    void shouldReturnByteArrayWhenSizeIsInLongBounds() {

        // given
        final byte[] expectedByteArray = new byte[] {1};

        // when
        final byte[] bytes = Preconditions.sizeMin(expectedByteArray, 1L);

        // then
        assertThat(bytes).isEqualTo(expectedByteArray);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsCollection() {

        // given
        final Collection<String> expectedCollection = Arrays.asList("1", "2", "3");

        // when & then
        assertThrows(
                IllegalArgumentException.class, () -> Preconditions.sizeMin(expectedCollection, 5));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsArray() {

        // given
        final String[] expectedArray = new String[] {"1", "2", "3"};

        // when & then
        assertThrows(IllegalArgumentException.class, () -> Preconditions.sizeMin(expectedArray, 5));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsCharSequence() {

        // given
        final CharSequence expectedCharSequence = "123";

        // when & then
        assertThrows(
                IllegalArgumentException.class,
                () -> Preconditions.sizeMin(expectedCharSequence, 5));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsByteArray() {

        // given
        final byte[] expectedByteArray = new byte[] {1, 2, 3};

        // when & then
        assertThrows(
                IllegalArgumentException.class, () -> Preconditions.sizeMin(expectedByteArray, 5));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSizeIsInLongBoundsByteArray() {

        // given
        final byte[] expectedByteArray = new byte[] {1, 2, 3};

        // when & then
        assertThrows(
                IllegalArgumentException.class, () -> Preconditions.sizeMin(expectedByteArray, 5L));
    }
}
