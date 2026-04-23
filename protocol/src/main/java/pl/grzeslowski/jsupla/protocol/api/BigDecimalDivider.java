package pl.grzeslowski.jsupla.protocol.api;

import static java.math.RoundingMode.HALF_EVEN;
import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PRIVATE)
public class BigDecimalDivider {
    public static final RoundingMode DEFAULT_ROUNDING_MODE = HALF_EVEN;
    private final BigDecimal divider;
    private final int scale;
    private final MathContext mathContext;

    /**
     * Creates a divider for integer protocol values that should be exposed as decimal values.
     *
     * <p>The decimal scale is inferred from the number of digits in {@code divider}, and both scale
     * adjustment and division use {@link #DEFAULT_ROUNDING_MODE}.
     *
     * @param divider value by which decoded integer values are divided
     * @param precision precision used by the division {@link MathContext}
     */
    public BigDecimalDivider(int divider, int precision) {
        this(
                new BigDecimal(divider),
                findScale(divider),
                new MathContext(precision, DEFAULT_ROUNDING_MODE));
    }

    private static int findScale(int divider) {
        return String.valueOf(Math.abs(divider)).length();
    }

    public BigDecimal divide(BigInteger value) {
        return new BigDecimal(value)
                .setScale(scale, DEFAULT_ROUNDING_MODE)
                .divide(divider, mathContext);
    }

    public BigDecimal divide(long value) {
        return divide(BigInteger.valueOf(value));
    }
}
