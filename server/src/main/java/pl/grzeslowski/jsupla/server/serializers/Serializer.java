package pl.grzeslowski.jsupla.server.serializers;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoToSend;
import pl.grzeslowski.jsupla.server.entities.responses.Response;

public interface Serializer<ResponseT extends Response, ProtoT extends ProtoToSend> {
    ProtoT serialize(ResponseT response);
}
