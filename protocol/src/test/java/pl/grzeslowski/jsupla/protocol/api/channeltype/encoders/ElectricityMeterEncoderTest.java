package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ElectricityMeterValue;

class ElectricityMeterEncoderTest {
    private final ElectricityMeterEncoder encoder = new ElectricityMeterEncoder();

    @Test
    void shouldFailBecauseImplementationIsNotProvided() {
        ElectricityMeterValue value =
                new ElectricityMeterValue(
                        BigInteger.valueOf(1),
                        BigInteger.valueOf(2),
                        BigDecimal.valueOf(3),
                        BigDecimal.valueOf(4),
                        Currency.getInstance(Locale.CANADA),
                        5,
                        6,
                        List.of());
        assertThatThrownBy(() -> encoder.encode(value))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
