package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@RequiredArgsConstructor
public class DecimalValue implements ChannelValue {
    BigDecimal value;

    public DecimalValue(final int value) {
        this(new BigDecimal(value));
    }

    public DecimalValue(final long value) {
        this(new BigDecimal(value));
    }

    public DecimalValue(final double value) {
        this(new BigDecimal(value));
    }

    public DecimalValue(final float value) {
        this(new BigDecimal(value));
    }

    public DecimalValue(final byte value) {
        this(new BigDecimal(value));
    }

    public DecimalValue(final short value) {
        this(new BigDecimal(value));
    }

    public DecimalValue(final String value) {
        this(new BigDecimal(value));
    }
}
