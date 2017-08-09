package pl.grzeslowski.jsupla.server.listeners;

import pl.grzeslowski.jsupla.server.entities.requests.Request;
import pl.grzeslowski.jsupla.server.entities.responses.Response;

public interface ListenersFactory {
    <RqT extends Request, RspT extends Response> RequestListener<RqT, RspT> getRequestListener(RqT request);
}
