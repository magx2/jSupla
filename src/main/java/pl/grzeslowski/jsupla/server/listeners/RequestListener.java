package pl.grzeslowski.jsupla.server.listeners;

import pl.grzeslowski.jsupla.server.entities.requests.Request;
import pl.grzeslowski.jsupla.server.entities.responses.Response;

import java.util.Optional;

public interface RequestListener<Rq extends Request, Rsp extends Response> {
    Optional<Rsp> onRequest(Rq request);
}
