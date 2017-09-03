package pl.grzeslowski.jsupla.server.api.ents;

import pl.grzeslowski.jsupla.server.impl.ents.FromServerProtoDataAndChannel;
import pl.grzeslowski.jsupla.server.impl.ents.SuplaDataPackageAndChannel;

import java.util.function.Function;

public interface SuplaDataPacketToFromServerProtoAndChannel
        extends Function<SuplaDataPackageAndChannel, FromServerProtoDataAndChannel> {
}
