package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.server.ents.channels.FromServerProtoChannel;

import static java.util.Objects.requireNonNull;

public final class FromServerProtoDataAndChannel {
    private final FromServerProto proto;
    private final FromServerProtoChannel channel;

    public FromServerProtoDataAndChannel(final FromServerProto proto, final FromServerProtoChannel channel) {
        this.proto = requireNonNull(proto);
        this.channel = requireNonNull(channel);
    }

    public FromServerProto getProto() {
        return proto;
    }

    public FromServerProtoChannel getChannel() {
        return channel;
    }
}
