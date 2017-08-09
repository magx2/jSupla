package pl.grzeslowski.jsupla.server.serializers;

import pl.grzeslowski.jsupla.protocol.Proto;
import pl.grzeslowski.jsupla.protocol.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.server.entities.responses.Response;
import pl.grzeslowski.jsupla.server.entities.responses.register_device.RegisterDeviceResponse;

import static java.lang.String.format;

public class SerializersFactoryImpl implements SerializersFactory {
    private final Serializer<RegisterDeviceResponse, SuplaRegisterDeviceResult> registerDeviceResponseSerializer = new RegisterDeviceResponseSerializer();

    @SuppressWarnings("unchecked")
    @Override
    public <Rsp extends Response> Serializer<Rsp, ? extends Proto> getSerializerForResponse(Rsp response) {
        if (response instanceof RegisterDeviceResponse) {
            return (Serializer<Rsp, ? extends Proto>) registerDeviceResponseSerializer;
        }

        throw new IllegalArgumentException(format("Can't find serializer for class %s!", response.getClass().getSimpleName()));
    }
}
