package pl.grzeslowski.jsupla.protocoljava.api.serializers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ServerDeviceEntity;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;

public interface ServerDeviceSerializer<EntityT extends ServerDeviceEntity, SuplaProtoT extends ServerDevice>
    extends Serializer<EntityT, SuplaProtoT> {
}
