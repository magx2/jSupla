package pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.DeviceClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.factories.serializers.SerializerFactory;

public interface DeviceClientServerSerializerFactory extends
        SerializerFactory<DeviceClientServerEntity, DeviceClientServer> {
}
