package pl.grzeslowski.jsupla.protocoljava.api.parsers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.ServerDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ServerDeviceEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;

public interface ServerDeviceEntityParser<EntityT extends ServerDeviceEntity, SuplaProtoT extends ServerDevice>
        extends Parser<EntityT, SuplaProtoT> {
}
