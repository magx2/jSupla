package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.server.entities.requests.Request;
import pl.grzeslowski.jsupla.server.ents.channels.SuplaDataPacketChannel;

public class RequestConnection extends SuplaConnection<Request> {
    public RequestConnection(final Request request, final SuplaDataPacketChannel channel) {
        super(request, channel);
    }
}
