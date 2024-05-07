package pl.grzeslowski.jsupla.protocol.api;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class PreconditionsTestSizeMin {
    @Test
    public void shouldReturnCollectionWhenSizeIsInBounds() throws Exception {

        // given
        final Collection<String> expectedCollection = Arrays.asList("1", "2");

        // when
        final Collection<String> collection = Preconditions.sizeMin(expectedCollection, 1);

        // then
        assertThat(collection).isEqualTo(expectedCollection);
    }

    @Test
    public void shouldReturnArrayWhenSizeIsInBounds() throws Exception {

        // given
        final String[] expectedArray = new String[]{"1", "2"};

        // when
        final String[] array = Preconditions.sizeMin(expectedArray, 1);

        // then
        assertThat(array).isEqualTo(expectedArray);
    }

    @Test
    public void shouldReturnCharSequenceWhenSizeIsInBounds() throws Exception {

        // given
        final CharSequence expectedCharSequence = " ";

        // when
        final CharSequence charSequence = Preconditions.sizeMin(expectedCharSequence, 1);

        // then
        assertThat(charSequence).isEqualTo(expectedCharSequence);
    }

    @Test
    public void shouldReturnByteArrayWhenSizeIsInBounds() throws Exception {

        // given
        final byte[] expectedByteArray = new byte[]{1};

        // when
        final byte[] bytes = Preconditions.sizeMin(expectedByteArray, 1);

        // then
        assertThat(bytes).isEqualTo(expectedByteArray);
    }

    @Test
    public void shouldReturnByteArrayWhenSizeIsInLongBounds() throws Exception {

        // given
        final byte[] expectedByteArray = new byte[]{1};

        // when
        final byte[] bytes = Preconditions.sizeMin(expectedByteArray, 1L);

        // then
        assertThat(bytes).isEqualTo(expectedByteArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsCollection() throws Exception {

        // given
        final Collection<String> expectedCollection = Arrays.asList("1", "2", "3");

        // when
        Preconditions.sizeMin(expectedCollection, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsArray() throws Exception {

        // given
        final String[] expectedArray = new String[]{"1", "2", "3"};

        // when
        Preconditions.sizeMin(expectedArray, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsCharSequence() throws Exception {

        // given
        final CharSequence expectedCharSequence = "123";

        // when
        Preconditions.sizeMin(expectedCharSequence, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsByteArray() throws Exception {

        // given
        final byte[] expectedByteArray = new byte[]{1, 2, 3};

        // when
        Preconditions.sizeMin(expectedByteArray, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenSizeIsInLongBoundsByteArray() throws Exception {

        // given
        final byte[] expectedByteArray = new byte[]{1, 2, 3};

        // when
        Preconditions.sizeMin(expectedByteArray, 5L);
    }
}