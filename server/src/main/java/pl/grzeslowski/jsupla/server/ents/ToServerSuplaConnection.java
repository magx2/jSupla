package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;
import pl.grzeslowski.jsupla.server.SuplaChannel;

public class ToServerSuplaConnection extends SuplaConnection<ToServerProto> {
    public ToServerSuplaConnection(final ToServerProto toServerProto, final SuplaChannel channel) {
        super(toServerProto, channel);
    }
}
