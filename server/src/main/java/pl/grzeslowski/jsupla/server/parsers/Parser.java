package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.server.entities.requests.Request;

public interface Parser<RequestT extends Request, ProtoT extends ProtoWithSize> {
    RequestT parse(ProtoT proto);
}
