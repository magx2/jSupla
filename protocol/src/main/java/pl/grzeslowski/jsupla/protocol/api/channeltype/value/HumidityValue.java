package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import lombok.NonNull;

import java.math.BigDecimal;

public record HumidityValue(@NonNull BigDecimal humidity) implements ChannelValue {
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
