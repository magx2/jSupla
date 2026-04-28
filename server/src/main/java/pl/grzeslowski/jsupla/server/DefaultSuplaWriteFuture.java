package pl.grzeslowski.jsupla.server;

import static java.util.Objects.requireNonNull;

import io.netty.channel.ChannelFuture;
import lombok.experimental.Delegate;

record DefaultSuplaWriteFuture(long msgId, ChannelFuture delegate) implements SuplaWriteFuture {
    DefaultSuplaWriteFuture {
        requireNonNull(delegate);
    }

    @Delegate
    private ChannelFuture delegateFuture() {
        return delegate;
    }
}
