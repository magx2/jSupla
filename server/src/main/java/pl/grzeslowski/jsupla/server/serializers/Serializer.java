package pl.grzeslowski.jsupla.server.serializers;

import pl.grzeslowski.jsupla.protocol.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.server.entities.responses.Response;

public interface Serializer<Rsp extends Response, SD extends ServerDevice> {
    SD serialize(Rsp entity);
}
