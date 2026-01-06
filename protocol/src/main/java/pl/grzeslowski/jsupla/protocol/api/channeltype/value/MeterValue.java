package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import java.math.BigDecimal;
import pl.grzeslowski.jsupla.protocol.api.MeterChartType;
import pl.grzeslowski.jsupla.protocol.api.MeterCounterType;

public record MeterValue(
        MeterCounterType type,
        BigDecimal valueDivider,
        BigDecimal valueMultiplier,
        BigDecimal valueAdded,
        int valuePrecision,
        String unitBefore,
        String unitAfter,
        boolean noSpaceBefore,
        boolean noSpaceAfter,
        boolean keepHistory,
        MeterChartType chartType,
        boolean includeValueAddedInHistory,
        boolean fillMissingData)
        implements ChannelValue {
    /*public BigDecimal value() {
        return
    }*/
}
