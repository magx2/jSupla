package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.math.BigDecimal;

public record RainValue(BigDecimal value) implements ChannelValue {}
