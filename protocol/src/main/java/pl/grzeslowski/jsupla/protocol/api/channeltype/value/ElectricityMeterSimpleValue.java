package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.math.BigDecimal;
import lombok.NonNull;

public record ElectricityMeterSimpleValue(
        @NonNull BigDecimal totalForwardActiveEnergy,
        boolean phase1,
        boolean phase2,
        boolean phase3)
        implements ChannelValue {}
