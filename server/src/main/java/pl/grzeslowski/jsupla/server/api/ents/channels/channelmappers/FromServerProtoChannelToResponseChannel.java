package pl.grzeslowski.jsupla.server.api.ents.channels.channelmappers;

import pl.grzeslowski.jsupla.server.api.ents.channels.FromServerProtoChannel;
import pl.grzeslowski.jsupla.server.api.ents.channels.ResponseChannel;

import java.util.function.Function;

public interface FromServerProtoChannelToResponseChannel extends Function<FromServerProtoChannel, ResponseChannel> {
}
