package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.server.SuplaChannel;
import pl.grzeslowski.jsupla.server.entities.requests.Request;

public class RequestConnection extends SuplaConnection<Request> {
    public RequestConnection(final Request request, final SuplaChannel channel) {
        super(request, channel);
    }
}
