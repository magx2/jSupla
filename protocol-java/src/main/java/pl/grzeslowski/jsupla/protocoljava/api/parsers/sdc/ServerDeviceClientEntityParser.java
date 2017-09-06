package pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.ServerDeviceClient;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.ServerDeviceClientEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;

public interface ServerDeviceClientEntityParser<EntityT extends ServerDeviceClientEntity,
                                                       SuplaProtoT extends ServerDeviceClient>
        extends Parser<EntityT, SuplaProtoT> {
}
