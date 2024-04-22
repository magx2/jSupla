package pl.grzeslowski.jsupla.protocoljava.api.serializers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.Serializer;

public interface ClientServerSerializer<EntityT extends ClientServerEntity, SuplaProtoT extends ClientServer>
    extends Serializer<EntityT, SuplaProtoT> {
}
