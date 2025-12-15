package pl.grzeslowski.jsupla.protocol.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PreconditionsBoundsTest {
    @Test
    void shouldReturnGivenNumberMin() {

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
    void shouldReturnGivenNumberMax() {

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

    @Test
    void shouldThrowErrorWhenValueIsTooSmallInt() {

        // given
        int value = 3;
        int minBound = 4;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> Preconditions.min(value, minBound));
    }

    @Test
    void shouldThrowErrorWhenValueIsTooSmallLong() {

        // given
        long value = 3;
        long minBound = 4;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> Preconditions.min(value, minBound));
    }

    @Test
    void shouldThrowErrorWhenValueIsTooSmallShort() {

        // given
        short value = 3;
        short minBound = 4;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> Preconditions.min(value, minBound));
    }

    @Test
    void shouldThrowErrorWhenValueIsTooBigInt() {

        // given
        int value = 3;
        int maxBound = 2;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> Preconditions.max(value, maxBound));
    }

    @Test
    void shouldThrowErrorWhenValueIsTooBigLong() {

        // given
        long value = 3;
        long maxBound = 2;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> Preconditions.max(value, maxBound));
    }

    @Test
    void shouldThrowErrorWhenValueIsTooBigShort() {

        // given
        short value = 3;
        short maxBound = 2;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> Preconditions.max(value, maxBound));
    }
}
