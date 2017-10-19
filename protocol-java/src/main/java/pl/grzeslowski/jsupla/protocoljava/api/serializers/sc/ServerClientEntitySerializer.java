package pl.grzeslowski.jsupla.protocoljava.api.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.ServerClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ServerClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;

public interface ServerClientEntitySerializer<EntityT extends ServerClientEntity, SuplaProtoT extends ServerClient>
        extends Serializer<EntityT, SuplaProtoT> {
}
