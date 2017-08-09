package pl.grzeslowski.jsupla.server.serializers;

import pl.grzeslowski.jsupla.protocol.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.server.entities.responses.Response;

public interface SerializersFactory {
    <Rsp extends Response> Serializer<Rsp, ? extends ServerDevice> getSerializerForResponse(Rsp response);
}
