package pl.grzeslowski.jsupla.protocoljava.api.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.Parser;

public interface DeviceServerParser<EntityT extends DeviceServerEntity, SuplaProtoT extends DeviceServer>
        extends Parser<EntityT, SuplaProtoT> {
}
