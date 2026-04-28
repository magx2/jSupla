package pl.grzeslowski.jsupla.server;

import io.netty.channel.ChannelFuture;

public interface SuplaWriteFuture extends ChannelFuture {
    long msgId();
}
