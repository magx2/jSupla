package pl.grzeslowski.jsupla.server.api;

import pl.grzeslowski.jsupla.protocol.api.types.ToServerProto;

public interface MessageHandler {
    void handle(ToServerProto toServerProto);

    void active(Writer writer);

    void inactive();
}
