package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;
import pl.grzeslowski.jsupla.server.SuplaChannel;

public final class ToServerProtoConnection extends SuplaConnection<ToServerProto> {
    public ToServerProtoConnection(final ToServerProto toServerProto, final SuplaChannel channel) {
        super(toServerProto, channel);
    }
}
