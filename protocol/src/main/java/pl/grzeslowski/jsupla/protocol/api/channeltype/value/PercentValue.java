package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import lombok.Value;

import static pl.grzeslowski.jsupla.Preconditions.size;

@Value
public class PercentValue implements ChannelValue {
    public static final PercentValue ZERO = new PercentValue(0);
    public static final PercentValue HUNDRED = new PercentValue(100);

    int value;

    public PercentValue(final int value) {
        this.value = size(value, 0, 100);
    }
}
