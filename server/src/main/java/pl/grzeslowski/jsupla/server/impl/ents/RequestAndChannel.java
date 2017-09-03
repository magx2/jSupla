package pl.grzeslowski.jsupla.server.impl.ents;

import pl.grzeslowski.jsupla.server.api.entities.requests.Request;
import pl.grzeslowski.jsupla.server.api.ents.channels.ResponseChannel;

import static java.util.Objects.requireNonNull;

public final class RequestAndChannel {
    private final Request request;
    private final ResponseChannel channel;

    public RequestAndChannel(final Request request, final ResponseChannel channel) {
        this.request = requireNonNull(request);
        this.channel = requireNonNull(channel);
    }

    public Request getRequest() {
        return request;
    }

    public ResponseChannel getChannel() {
        return channel;
    }
}
