package pl.grzeslowski.jsupla.protocoljava.api.parsers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.VersionError;

public interface VersionErrorParser extends ServerDeviceClientEntityParser<VersionError, SuplaVersionError> {
}
