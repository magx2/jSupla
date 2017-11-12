package pl.grzeslowski.jsupla.protocoljava.api.serializers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrlResult;

public interface FirmwareUpdateUrlResultSerializer
        extends ServerDeviceSerializer<FirmwareUpdateUrlResult, SuplaFirmwareUpdateUrlResult> {
}
