package pl.grzeslowski.jsupla.protocol.api.channeltype;

import java.util.Set;
import pl.grzeslowski.jsupla.protocol.api.BitFunction;
import pl.grzeslowski.jsupla.protocol.api.ChannelFlag;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;

public record ChannelDescription(
        ChannelType type, Set<ChannelFlag> flags, Set<BitFunction> functions) {}
