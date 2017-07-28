package pl.grzeslowski.jsupla.server.serializers;

import pl.grzeslowski.jsupla.proto.Proto;
import pl.grzeslowski.jsupla.proto.structs.sd.TSD_SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.server.entities.responses.RegisterDeviceResponse;
import pl.grzeslowski.jsupla.server.entities.responses.Response;

import static java.lang.String.format;

public class SerializersFactoryImpl implements SerializersFactory {
    private final Serializer<RegisterDeviceResponse, TSD_SuplaRegisterDeviceResult> registerDeviceResponseSerializer = new RegisterDeviceResponseSerializer();

    @SuppressWarnings("unchecked")
    @Override
    public <Rsp extends Response> Serializer<Rsp, ? extends Proto> getSerializerForResponse(Rsp response) {
        if (response instanceof RegisterDeviceResponseSerializer) {
            return (Serializer<Rsp, ? extends Proto>) registerDeviceResponseSerializer;
        }

        throw new IllegalArgumentException(format("Can't find serializer for class %s!", response.getClass().getSimpleName()));
    }
}
