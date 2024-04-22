package pl.grzeslowski.jsupla.protocoljava.api.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.ServerClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ServerClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;

public interface ServerClientParser<EntityT extends ServerClientEntity, SuplaProtoT extends ServerClient>
    extends Parser<EntityT, SuplaProtoT> {
}
