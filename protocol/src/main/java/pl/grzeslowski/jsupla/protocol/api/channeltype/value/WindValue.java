package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.math.BigDecimal;

public record WindValue(BigDecimal value) implements ChannelValue {}
