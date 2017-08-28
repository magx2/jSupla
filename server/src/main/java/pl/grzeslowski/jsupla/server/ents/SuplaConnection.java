package pl.grzeslowski.jsupla.server.ents;

import pl.grzeslowski.jsupla.server.SuplaChannel;

public abstract class SuplaConnection<T> {
    private final T type;
    private final SuplaChannel channel;

    public SuplaConnection(final T type, final SuplaChannel channel) {
        // TODO
        //        this.request = requireNonNull(request);
        //        this.channel = requireNonNull(channel);
        this.type = type;
        this.channel = channel;
    }

    public T value() {
        return type;
    }

    public SuplaChannel getChannel() {
        return channel;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuplaConnection)) {
            return false;
        }

        final SuplaConnection<?> that = (SuplaConnection<?>) o;

        if (!type.equals(that.type)) return false;
        return getChannel().equals(that.getChannel());
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + getChannel().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SuplaConnection{" +
                       "type=" + type +
                       ", channel=" + channel +
                       '}';
    }
}
