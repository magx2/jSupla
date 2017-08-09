package pl.grzeslowski.jsupla.server.serializers;

import pl.grzeslowski.jsupla.protocol.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.server.entities.responses.Response;

public interface Serializer<ResponseT extends Response, ServerDeviceT extends ServerDevice> {
    ServerDeviceT serialize(ResponseT response);
}
