package pl.grzeslowski.jsupla.protocol.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pl.grzeslowski.jsupla.protocol.api.ProtocolHelpers.toSignedInt;

import org.junit.jupiter.api.Test;

class ProtocolHelpersTest {
    @Test
    void shouldConvert0LTo0() {
        assertThat(toSignedInt(0L)).isZero();
    }

    @Test
    void shouldConvertPositiveValue() {
        assertThat(toSignedInt(123L)).isEqualTo(123);
    }

    @Test
    void shouldConvertIntegerMaxValue() {
        assertThat(toSignedInt(Integer.MAX_VALUE)).isEqualTo(Integer.MAX_VALUE);
    }

    @Test
    void shouldConvertToNegative() {
        assertThat(toSignedInt(2147483648L)).isEqualTo(Integer.MIN_VALUE);
    }

    @Test
    void shouldConvertMaxUnsignedInt() {
        // 0xFFFFFFFFL
        assertThat(toSignedInt(4294967295L)).isEqualTo(-1);
    }

    @Test
    void shouldThrowExceptionForNegativeInput() {
        assertThatThrownBy(() -> toSignedInt(-1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Given value -1 is smaller than minimal value 0!");
    }

    @Test
    void shouldThrowExceptionForTooLargeInput() {
        assertThatThrownBy(() -> toSignedInt(4294967296L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Given value 4294967296 is bigger than maximal value 4294967295!");
    }
}
