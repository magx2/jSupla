package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;
import pl.grzeslowski.jsupla.server.ents.channels.SuplaDataPacketChannel;

public final class ToServerProtoConnection extends SuplaConnection<ToServerProto> {
    public ToServerProtoConnection(final ToServerProto toServerProto, final SuplaDataPacketChannel channel) {
        super(toServerProto, channel);
    }
}
