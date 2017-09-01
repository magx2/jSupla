package pl.grzeslowski.jsupla.server.serializers;

import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.server.entities.responses.Response;

public interface Serializer<ResponseT extends Response, ProtoT extends FromServerProto> {
    ProtoT serialize(ResponseT response);
}
