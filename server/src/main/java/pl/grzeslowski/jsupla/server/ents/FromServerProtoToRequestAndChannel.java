package pl.grzeslowski.jsupla.server.ents;

import java.util.function.Function;

public interface FromServerProtoToRequestAndChannel extends Function<FromServerProtoDataAndChannel, RequestAndChannel> {
}
