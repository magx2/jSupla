package pl.grzeslowski.jsupla.server.impl.serializers;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaRegisterDeviceResult;
import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;
import pl.grzeslowski.jsupla.server.api.entities.responses.ActivityTimeoutResponse;
import pl.grzeslowski.jsupla.server.api.entities.responses.Response;
import pl.grzeslowski.jsupla.server.api.entities.responses.registerdevice.RegisterDeviceResponse;
import pl.grzeslowski.jsupla.server.api.serializers.Serializer;
import pl.grzeslowski.jsupla.server.api.serializers.SerializersFactory;

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
