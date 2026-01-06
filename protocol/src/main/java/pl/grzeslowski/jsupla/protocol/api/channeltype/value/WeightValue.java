package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.math.BigDecimal;

public record WeightValue(BigDecimal value) implements ChannelValue {}
