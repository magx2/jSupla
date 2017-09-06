package pl.grzeslowski.jsupla.protocoljava.api.serializers.sdc;

import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sdc.VersionError;

public interface VersionErrorSerializer extends ServerDeviceClientEntitySerializer<VersionError, SuplaVersionError> {
}
