package pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.ServerDeviceClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.ServerDeviceClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.SerializerFactory;

public interface ServerDeviceClientSerializerFactory extends
        SerializerFactory<ServerDeviceClientEntity, ServerDeviceClient> {
}
