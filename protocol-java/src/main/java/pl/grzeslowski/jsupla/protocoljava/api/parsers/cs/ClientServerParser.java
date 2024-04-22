package pl.grzeslowski.jsupla.protocoljava.api.parsers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.ClientServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;

public interface ClientServerParser<EntityT extends ClientServerEntity, SuplaProtoT extends ClientServer>
    extends Parser<EntityT, SuplaProtoT> {
}
