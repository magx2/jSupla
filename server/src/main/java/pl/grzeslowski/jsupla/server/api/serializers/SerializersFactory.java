package pl.grzeslowski.jsupla.server.api.serializers;

import pl.grzeslowski.jsupla.protocol.api.types.FromServerProto;
import pl.grzeslowski.jsupla.server.api.entities.responses.Response;

public interface SerializersFactory {
    <ResponseT extends Response> Serializer<ResponseT, ? extends FromServerProto> getSerializerForResponse(
            ResponseT response);
}
