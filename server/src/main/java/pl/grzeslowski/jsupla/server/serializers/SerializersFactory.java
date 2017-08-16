package pl.grzeslowski.jsupla.server.serializers;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.server.entities.responses.Response;

public interface SerializersFactory {
    <ResponseT extends Response> Serializer<ResponseT, ? extends ProtoWithSize> getSerializerForResponse(
            ResponseT response);
}
