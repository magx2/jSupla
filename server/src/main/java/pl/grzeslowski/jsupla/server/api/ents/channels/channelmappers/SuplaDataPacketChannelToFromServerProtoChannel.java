package pl.grzeslowski.jsupla.server.api.ents.channels.channelmappers;

import pl.grzeslowski.jsupla.server.api.ents.channels.FromServerProtoChannel;
import pl.grzeslowski.jsupla.server.api.ents.channels.SuplaDataPacketChannel;

import java.util.function.Function;

public interface SuplaDataPacketChannelToFromServerProtoChannel
        extends Function<SuplaDataPacketChannel, FromServerProtoChannel> {
}
