package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.math.BigDecimal;

public record PressureValue(BigDecimal value) implements ChannelValue {}
