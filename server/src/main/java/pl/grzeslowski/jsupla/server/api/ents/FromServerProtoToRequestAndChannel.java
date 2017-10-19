package pl.grzeslowski.jsupla.server.api.ents;

import pl.grzeslowski.jsupla.server.impl.ents.FromServerProtoDataAndChannel;
import pl.grzeslowski.jsupla.server.impl.ents.RequestAndChannel;

import java.util.function.Function;

public interface FromServerProtoToRequestAndChannel extends Function<FromServerProtoDataAndChannel, RequestAndChannel> {
}