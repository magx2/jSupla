package pl.grzeslowski.jsupla.server;

import jakarta.annotation.Nonnull;
import lombok.*;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;

public interface SuplaWriter {
    SuplaWriteFuture write(@Nonnull FromServerProto proto);

    short getVersion();
}
