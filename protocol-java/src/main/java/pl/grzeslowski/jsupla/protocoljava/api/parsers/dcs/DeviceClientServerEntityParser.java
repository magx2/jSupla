package pl.grzeslowski.jsupla.protocoljava.api.parsers.dcs;

import pl.grzeslowski.jsupla.protocol.api.structs.dcs.DeviceClientServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.dcs.DeviceClientServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;

public interface DeviceClientServerEntityParser<EntityT extends DeviceClientServerEntity,
                                                 SuplaProtoT extends DeviceClientServer>
        extends Parser<EntityT, SuplaProtoT> {
}
