package pl.grzeslowski.jsupla.server.serializers;

import pl.grzeslowski.jsupla.protocol.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.types.ProtoWithSize;
import pl.grzeslowski.jsupla.server.entities.responses.ActivityTimeoutResponse;
import pl.grzeslowski.jsupla.server.entities.responses.Response;
import pl.grzeslowski.jsupla.server.entities.responses.registerdevice.RegisterDeviceResponse;

import static java.lang.String.format;

public class SerializersFactoryImpl implements SerializersFactory {
    private final Serializer<RegisterDeviceResponse, SuplaRegisterDeviceResult> registerDeviceResponseSerializer =
            new RegisterDeviceResponseSerializer();

    @SuppressWarnings("unchecked")
    @Override
    public <ResponseT extends Response> Serializer<ResponseT, ? extends ProtoWithSize> getSerializerForResponse(
            ResponseT response) {
        if (response instanceof RegisterDeviceResponse) {
            return (Serializer<ResponseT, ? extends ProtoWithSize>) registerDeviceResponseSerializer;
        } else if (response instanceof ActivityTimeoutResponse) {
            return (Serializer<ResponseT, ? extends ProtoWithSize>) new ActivityTimeoutResponseSerializer();
        }

        throw new IllegalArgumentException(
                format("Can't find serializer for class %s!", response.getClass().getSimpleName()));
    }
}
