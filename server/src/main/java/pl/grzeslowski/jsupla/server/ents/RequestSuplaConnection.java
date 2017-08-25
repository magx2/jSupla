package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.server.SuplaChannel;
import pl.grzeslowski.jsupla.server.entities.requests.Request;

public class RequestSuplaConnection extends SuplaConnection<Request> {
    public RequestSuplaConnection(final Request request, final SuplaChannel channel) {
        super(request, channel);
    }
}
