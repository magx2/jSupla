package pl.grzeslowski.jsupla.protocoljava.api.serializers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.DeviceClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;

public interface DeviceClientServerSerializer<EntityT extends DeviceClientServerEntity,
                                                     SuplaProtoT extends DeviceClientServer>
        extends Serializer<EntityT, SuplaProtoT> {
}
