package pl.grzeslowski.jsupla.protocol.api.channeltype.value;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

import java.util.Set;
import lombok.NonNull;
import pl.grzeslowski.jsupla.protocol.api.HvacFlag;
import pl.grzeslowski.jsupla.protocol.api.HvacMode;

/**
 * @param on
 * @param mode Use {@code SUPLA_HVAC_MODE_NOT_SET} if you don't want to modify current mode, but only to alter temperature set points
 * @param setPointTemperatureHeat
 * @param setPointTemperatureCool
 * @param flags
 */
public record HvacValue(
        boolean on,
        @NonNull HvacMode mode,
        Double setPointTemperatureHeat,
        Double setPointTemperatureCool,
        @NonNull Set<HvacFlag> flags)
        implements ChannelValue {}
