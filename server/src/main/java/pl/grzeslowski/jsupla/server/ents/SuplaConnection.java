package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.server.SuplaChannel;

public abstract class SuplaConnection<T> {
    private final T t;
    private final SuplaChannel channel;

    public SuplaConnection(final T t, final SuplaChannel channel) {
        // TODO
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof SuplaConnection)) return false;

        final SuplaConnection<?> that = (SuplaConnection<?>) o;

        if (!t.equals(that.t)) return false;
        return getChannel().equals(that.getChannel());
    }

    @Override
    public int hashCode() {
        int result = t.hashCode();
        result = 31 * result + getChannel().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SuplaConnection{" +
                       "t=" + t +
                       ", channel=" + channel +
                       '}';
    }
}
