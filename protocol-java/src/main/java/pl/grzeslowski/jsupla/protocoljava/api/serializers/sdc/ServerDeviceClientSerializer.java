package pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.ServerDeviceClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.ServerDeviceClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;

public interface ServerDeviceClientSerializer<EntityT extends ServerDeviceClientEntity,
    SuplaProtoT extends ServerDeviceClient>
    extends Serializer<EntityT, SuplaProtoT> {
}
