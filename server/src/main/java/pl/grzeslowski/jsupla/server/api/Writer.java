package pl.grzeslowski.jsupla.server.api;

import io.netty.channel.ChannelFuture;
import jakarta.annotation.Nonnull;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;

public interface Writer {
    ChannelFuture write(@Nonnull FromServerProto proto);

    short getVersion();

    void setVersion(short version);
}
