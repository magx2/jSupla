package pl.grzeslowski.jsupla.server.serializers;

import pl.grzeslowski.jsupla.proto.Proto;
import pl.grzeslowski.jsupla.server.entities.responses.Response;

public interface SerializersFactory {
    <Rsp extends Response> Serializer<Rsp, ? extends Proto> getSerializerForResponse(Rsp response);
}
