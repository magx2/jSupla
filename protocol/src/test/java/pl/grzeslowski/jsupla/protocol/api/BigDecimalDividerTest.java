package pl.grzeslowski.jsupla.protocol.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class BigDecimalDividerTest {
    private static final int ELECTRICITY_METER_PRECISION = 64;

    @Test
    void shouldDivideBigIntegerUsingConfiguredDividerAndScale() {
        // given
        final BigDecimalDivider divider = new BigDecimalDivider(100, 5);

        // when
        final BigDecimal value = divider.divide(new BigInteger("12345"));

        // then
        assertThat(value).isEqualByComparingTo(new BigDecimal("123.45"));
    }

    @Test
    void shouldPreserveNegativeValues() {
        // given
        final BigDecimalDivider divider = new BigDecimalDivider(10, 2);

        // when
        final BigDecimal value = divider.divide(new BigInteger("-55"));

        // then
        assertThat(value).isEqualByComparingTo(new BigDecimal("-5.5"));
    }

    @Test
    void shouldApplyMathContextWhenDivisionRequiresRounding() {
        // given
        final BigDecimalDivider divider = new BigDecimalDivider(3, 3);

        // when
        final BigDecimal value = divider.divide(new BigInteger("10"));

        // then
        assertThat(value).isEqualByComparingTo(new BigDecimal("3.33"));
    }

    @Test
    void shouldInferScaleFromDividerDigits() {
        // given
        final BigDecimalDivider divider = new BigDecimalDivider(1000, 5);

        // when
        final BigDecimal value = divider.divide(new BigInteger("12345"));

        // then
        assertThat(value).isEqualByComparingTo(new BigDecimal("12.345"));
    }

    @Test
    void shouldDecodeElectricityMeterEnergyMultiplier() {
        // given
        final BigDecimalDivider divider =
                new BigDecimalDivider(100_000, ELECTRICITY_METER_PRECISION);

        // when
        final BigDecimal value = divider.divide(new BigInteger("200000"));

        // then
        assertThat(value).isEqualByComparingTo(new BigDecimal("2"));
    }

    @Test
    void shouldDecodeElectricityMeterPricePerUnitMultiplier() {
        // given
        final BigDecimalDivider divider =
                new BigDecimalDivider(10_000, ELECTRICITY_METER_PRECISION);

        // when
        final BigDecimal value = divider.divide(new BigInteger("45678"));

        // then
        assertThat(value).isEqualByComparingTo(new BigDecimal("4.5678"));
    }

    @Test
    void shouldDecodeElectricityMeterMeasurementMultipliers() {
        assertThat(
                        new BigDecimalDivider(100, ELECTRICITY_METER_PRECISION)
                                .divide(new BigInteger("23000")))
                .isEqualByComparingTo(new BigDecimal("230"));
        assertThat(
                        new BigDecimalDivider(1_000, ELECTRICITY_METER_PRECISION)
                                .divide(new BigInteger("1000")))
                .isEqualByComparingTo(BigDecimal.ONE);
        assertThat(
                        new BigDecimalDivider(100_000, ELECTRICITY_METER_PRECISION)
                                .divide(new BigInteger("100000")))
                .isEqualByComparingTo(BigDecimal.ONE);
        assertThat(
                        new BigDecimalDivider(10, ELECTRICITY_METER_PRECISION)
                                .divide(new BigInteger("100")))
                .isEqualByComparingTo(BigDecimal.TEN);
    }
}
