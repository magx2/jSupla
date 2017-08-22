package pl.grzeslowski.jsupla;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PreconditionsBoundsTest {
    @Test
    public void shouldReturnGivenNumberMin() throws Exception {

        // given
        int value = 3;
        int minBound = 2;

        // when
        final int intMin = Preconditions.min(value, minBound);
        final long longMin = Preconditions.min((long) value, (long) minBound);
        final short shortMin = Preconditions.min((short) value, (short) minBound);

        // then
        assertThat(intMin).isEqualTo(value);
        assertThat(longMin).isEqualTo((long) value);
        assertThat(shortMin).isEqualTo((short) value);
    }

    @Test
    public void shouldReturnGivenNumberMax() throws Exception {

        // given
        int value = 3;
        int maxBound = 4;

        // when
        final int intMin = Preconditions.max(value, maxBound);
        final long longMin = Preconditions.max((long) value, (long) maxBound);
        final short shortMin = Preconditions.max((short) value, (short) maxBound);

        // then
        assertThat(intMin).isEqualTo(value);
        assertThat(longMin).isEqualTo((long) value);
        assertThat(shortMin).isEqualTo((short) value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWhenValueIsTooSmallInt() throws Exception {

        // given
        int value = 3;
        int minBound = 4;

        // when
        Preconditions.min(value, minBound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWhenValueIsTooSmallLong() throws Exception {

        // given
        long value = 3;
        long minBound = 4;

        // when
        Preconditions.min(value, minBound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWhenValueIsTooSmallShort() throws Exception {

        // given
        short value = 3;
        short minBound = 4;

        // when
        Preconditions.min(value, minBound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWhenValueIsTooBigInt() throws Exception {

        // given
        int value = 3;
        int maxBound = 2;

        // when
        Preconditions.max(value, maxBound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWhenValueIsTooBigLong() throws Exception {

        // given
        long value = 3;
        long maxBound = 2;

        // when
        Preconditions.max(value, maxBound);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowErrorWhenValueIsTooBigShort() throws Exception {

        // given
        short value = 3;
        short maxBound = 2;

        // when
        Preconditions.max(value, maxBound);
    }
}