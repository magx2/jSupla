package pl.grzeslowski.jsupla.server.api;

import jakarta.annotation.Nonnull;
import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;

public interface Writer {
    Future write(@Nonnull FromServerProto proto);

    interface Future {
        void addCompleteListener(@Nonnull Runnable listener);
    }
}
