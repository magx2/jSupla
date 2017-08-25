package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.server.SuplaChannel;

public abstract class SuplaConnection<T> {
    private final T t;
    private final SuplaChannel channel;

    public SuplaConnection(final T t, final SuplaChannel channel) {
//        this.request = requireNonNull(request);
//        this.channel = requireNonNull(channel);
        this.t = t;
        this.channel = channel;
    }

    public T value() {
        return t;
    }

    public SuplaChannel getChannel() {
        return channel;
    }
}
