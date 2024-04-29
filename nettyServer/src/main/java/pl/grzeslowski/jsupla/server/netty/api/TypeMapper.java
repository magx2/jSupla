package pl.grzeslowski.jsupla.server.netty.api;

import pl.grzeslowski.jsupla.protocol.api.ChannelType;

public interface TypeMapper {
    ChannelType findChannelType(int channelNumber);
}
