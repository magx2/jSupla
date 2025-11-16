package pl.grzeslowski.jsupla.protocol.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;

public class PreconditionsTestSize {
    @Test
    public void shouldReturnCollectionWhenSizeIsInBounds() throws Exception {

        // given
        final Collection<String> expectedCollection = Arrays.asList("1", "2");

        // when
        final Collection<String> collection = Preconditions.size(expectedCollection, 1, 2);

        // then
        assertThat(collection).isEqualTo(expectedCollection);
    }

    @Test
    public void shouldReturnArrayWhenSizeIsInBounds() throws Exception {

        // given
        final String[] expectedArray = new String[] {"1", "2"};

        // when
        final String[] array = Preconditions.size(expectedArray, 1, 2);

        // then
        assertThat(array).isEqualTo(expectedArray);
    }

    @Test
    public void shouldReturnNumberWhenSizeIsInBounds() throws Exception {

        // given
        final int expectedNumber = 1;

        // when
        final int number = Preconditions.size(expectedNumber, 1, 2);

        // then
        assertThat(number).isEqualTo(expectedNumber);
    }

    @Test
    public void shouldReturnCharSequenceWhenSizeIsInBounds() throws Exception {

        // given
        final CharSequence expectedCharSequence = " ";

        // when
        final CharSequence charSequence = Preconditions.size(expectedCharSequence, 1, 2);

        // then
        assertThat(charSequence).isEqualTo(expectedCharSequence);
    }

    @Test
    public void shouldReturnByteArrayWhenSizeIsInBounds() throws Exception {

        // given
        final byte[] expectedByteArray = new byte[] {1};

        // when
        final byte[] bytes = Preconditions.size(expectedByteArray, 1, 2);

        // then
        assertThat(bytes).isEqualTo(expectedByteArray);
    }

    @Test
    public void shouldReturnByteArrayWhenSizeIsInLongBounds() throws Exception {

        // given
        final byte[] expectedByteArray = new byte[] {1};

        // when
        final byte[] bytes = Preconditions.size(expectedByteArray, 1L, 2L);

        // then
        assertThat(bytes).isEqualTo(expectedByteArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsCollection() throws Exception {

        // given
        final Collection<String> expectedCollection = Arrays.asList("1", "2", "3");

        // when
        Preconditions.size(expectedCollection, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsArray() throws Exception {

        // given
        final String[] expectedArray = new String[] {"1", "2", "3"};

        // when
        Preconditions.size(expectedArray, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsNumber() throws Exception {

        // given
        final int expectedNumber = 3;

        // when
        Preconditions.size(expectedNumber, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsCharSequence()
            throws Exception {

        // given
        final CharSequence expectedCharSequence = "123";

        // when
        Preconditions.size(expectedCharSequence, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenSizeIsInBoundsByteArray() throws Exception {

        // given
        final byte[] expectedByteArray = new byte[] {1, 2, 3};

        // when
        Preconditions.size(expectedByteArray, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenSizeIsInLongBoundsByteArray()
            throws Exception {

        // given
        final byte[] expectedByteArray = new byte[] {1, 2, 3};

        // when
        Preconditions.size(expectedByteArray, 1L, 2L);
    }
}
