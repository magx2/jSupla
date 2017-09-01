package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;
import pl.grzeslowski.jsupla.server.ents.channels.SuplaDataPacketChannel;

public class ToServerSuplaConnection extends SuplaConnection<ToServerProto> {
    public ToServerSuplaConnection(final ToServerProto toServerProto, final SuplaDataPacketChannel channel) {
        super(toServerProto, channel);
    }
}
