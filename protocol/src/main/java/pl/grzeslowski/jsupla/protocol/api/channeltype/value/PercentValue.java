package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static pl.grzeslowski.jsupla.protocol.api.Preconditions.size;

/**
 * @param value
 */
public record PercentValue(int value) implements ChannelValue {
    public static final PercentValue ZERO = new PercentValue(0);
    public static final PercentValue HUNDRED = new PercentValue(100);

    public PercentValue {
        size(value, 0, 100);
    }
}
