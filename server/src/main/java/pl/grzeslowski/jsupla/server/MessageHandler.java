package pl.grzeslowski.jsupla.server;

import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;

public interface MessageHandler {
    void handle(ToServerProto toServerProto);

    void active(SuplaWriter writer);

    void inactive();

    void socketException(Throwable exception);
}
