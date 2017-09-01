package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.server.entities.requests.Request;
import pl.grzeslowski.jsupla.server.ents.channels.ResponseChannel;

import static java.util.Objects.requireNonNull;

public final class RequestChannel {
    private final Request request;
    private final ResponseChannel channel;

    public RequestChannel(final Request request, final ResponseChannel channel) {
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
