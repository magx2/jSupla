package pl.grzeslowski.jsupla.server;

import pl.grzeslowski.jsupla.server.entities.requests.Request;

public final class SuplaConnection {
    private final Request request;
    private final SuplaChannel channel;

    public SuplaConnection(final Request request, final SuplaChannel channel) {
//        this.request = requireNonNull(request);
//        this.channel = requireNonNull(channel);
        this.request = request;
        this.channel = channel;
    }

    public Request getRequest() {
        return request;
    }

    public SuplaChannel getChannel() {
        return channel;
    }
}
