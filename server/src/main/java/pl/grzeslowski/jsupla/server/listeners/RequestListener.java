package pl.grzeslowski.jsupla.server.listeners;

import pl.grzeslowski.jsupla.server.entities.requests.Request;
import pl.grzeslowski.jsupla.server.entities.responses.Response;

import java.util.Optional;

public interface RequestListener<RequestT extends Request, ResponseT extends Response> {
    Optional<ResponseT> onRequest(RequestT request);
}
