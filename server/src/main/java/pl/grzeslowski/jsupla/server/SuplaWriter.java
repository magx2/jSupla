package pl.grzeslowski.jsupla.server;

import io.netty.channel.ChannelFuture;
import jakarta.annotation.Nonnull;
import lombok.*;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;

public interface SuplaWriter {
    ChannelFuture write(@Nonnull FromServerProto proto);

    short getVersion();
}
