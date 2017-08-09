package pl.grzeslowski.jsupla.server.listeners;

import pl.grzeslowski.jsupla.server.entities.requests.Request;
import pl.grzeslowski.jsupla.server.entities.responses.Response;

public interface ListenersFactory {
    <Rq extends Request, Rsp extends Response> RequestListener<Rq, Rsp> getRequestListener(Rq request);
}
