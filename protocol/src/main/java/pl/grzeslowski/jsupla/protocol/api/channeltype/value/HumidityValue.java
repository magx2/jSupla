package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static java.math.BigDecimal.ZERO;
import static pl.grzeslowski.jsupla.protocol.api.Preconditions.size;

import java.math.BigDecimal;
import lombok.NonNull;

public record HumidityValue(@NonNull BigDecimal humidity) implements ChannelValue {
    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    public HumidityValue {
        size(humidity, ZERO, ONE_HUNDRED);
    }

    public HumidityValue(final int humidity) {
        this(BigDecimal.valueOf(humidity));
    }

    public HumidityValue(final long humidity) {
        this(BigDecimal.valueOf(humidity));
    }

    public HumidityValue(final double humidity) {
        this(BigDecimal.valueOf(humidity));
    }

    public HumidityValue(final float humidity) {
        this(BigDecimal.valueOf(humidity));
    }

    public HumidityValue(final byte humidity) {
        this(BigDecimal.valueOf(humidity));
    }

    public HumidityValue(final short humidity) {
        this(BigDecimal.valueOf(humidity));
    }

    public HumidityValue(final String humidity) {
        this(new BigDecimal(humidity));
    }
}
