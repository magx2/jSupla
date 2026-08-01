package pl.grzeslowski.jsupla.protocol.api.channeltype;

import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNullElse;
import static pl.grzeslowski.jsupla.protocol.api.ChannelFunction.SUPLA_CHANNELFNC_NONE;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import pl.grzeslowski.jsupla.protocol.api.ChannelFlag;
import pl.grzeslowski.jsupla.protocol.api.ChannelFunction;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;

public record ChannelDescription(
        ChannelType type,
        Set<ChannelFlag> flags,
        List<ChannelFunction> functions,
        Optional<ChannelFunction> selectedFunction) {
    public ChannelDescription {
        flags = requireNonNullElse(flags, Set.of());
        functions = List.copyOf(requireNonNullElse(functions, emptyList()));
        selectedFunction = requireNonNullElse(selectedFunction, Optional.empty());
    }

    public static ChannelDescription fromValues(
            ChannelType type, Set<ChannelFlag> flags, Integer funcList, int defaultValue) {
        return new ChannelDescription(
                type,
                flags,
                funcList == null
                        ? List.of()
                        : ChannelFunction.findByMask(funcList).stream().toList(),
                ChannelFunction.findByValue(defaultValue)
                        .filter(function -> function != SUPLA_CHANNELFNC_NONE));
    }
}
