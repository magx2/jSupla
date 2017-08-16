package pl.grzeslowski.jsupla.server.parsers;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.server.entities.requests.Request;

public interface ParsersFactory {
    <ProtoT extends ProtoWithSize> Parser<? extends Request, ProtoT> getParser(ProtoT deviceServer);
}
