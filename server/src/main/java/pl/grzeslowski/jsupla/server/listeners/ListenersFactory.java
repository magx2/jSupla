package pl.grzeslowski.jsupla.server.listeners;

import pl.grzeslowski.jsupla.server.entities.requests.Request;
import pl.grzeslowski.jsupla.server.entities.responses.Response;

public interface ListenersFactory {
    <RequestT extends Request, ResponseT extends Response> RequestListener<RequestT, ResponseT> getRequestListener(RequestT request);
}
