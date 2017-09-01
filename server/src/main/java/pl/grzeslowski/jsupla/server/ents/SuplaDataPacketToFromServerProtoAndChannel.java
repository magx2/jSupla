package pl.grzeslowski.jsupla.server.ents;

import java.util.function.Function;

public interface SuplaDataPacketToFromServerProtoAndChannel
        extends Function<SuplaDataPackageAndChannel, FromServerProtoDataAndChannel> {
}
