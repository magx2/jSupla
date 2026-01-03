package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class RgbValueTest {
    @Test
    void shouldUseDefaultCommandAndSubject() {
        var value = new RgbValue(1, 2, 3, 4, 5, 6);

        assertThat(value.command()).isEqualTo(RgbValue.Command.NOT_SET);
        assertThat(value.subject()).isEqualTo(RgbValue.Subject.UNKNOWN);
    }

    @Test
    void shouldValidateBrightnessRange() {
        assertThatThrownBy(() -> new RgbValue(101, 0, 0, 0, 0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Given value 101 is bigger than maximal value 100!");
        assertThatThrownBy(() -> new RgbValue(0, 101, 0, 0, 0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Given value 101 is bigger than maximal value 100!");
        assertThatThrownBy(() -> new RgbValue(0, 0, 0, 0, 0, 101))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Given value 101 is bigger than maximal value 100!");
    }

    @Test
    void shouldParseUnknownCommandAndSubjectToDefaults() {
        assertThat(RgbValue.Command.parse(99)).isEqualTo(RgbValue.Command.NOT_SET);
        assertThat(RgbValue.Subject.parse(99)).isEqualTo(RgbValue.Subject.UNKNOWN);
    }
}
