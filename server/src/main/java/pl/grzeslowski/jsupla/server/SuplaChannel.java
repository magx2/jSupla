package pl.grzeslowski.jsupla.server;

import pl.grzeslowski.jsupla.server.entities.responses.Response;

public interface SuplaChannel {
    void write(Response response);
}
