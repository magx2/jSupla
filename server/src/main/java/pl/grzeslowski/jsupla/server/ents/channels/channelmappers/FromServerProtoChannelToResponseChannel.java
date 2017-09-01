package pl.grzeslowski.jsupla.server.ents.channels.channelmappers;

import pl.grzeslowski.jsupla.server.ents.channels.FromServerProtoChannel;
import pl.grzeslowski.jsupla.server.ents.channels.ResponseChannel;

import java.util.function.Function;

public interface FromServerProtoChannelToResponseChannel extends Function<FromServerProtoChannel, ResponseChannel> {
}
