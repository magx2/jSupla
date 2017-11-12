package pl.grzeslowski.jsupla.protocoljava.api.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;

public interface DeviceServerSerializer<EntityT extends DeviceServerEntity, SuplaProtoT extends DeviceServer>
        extends Serializer<EntityT, SuplaProtoT> {
}
