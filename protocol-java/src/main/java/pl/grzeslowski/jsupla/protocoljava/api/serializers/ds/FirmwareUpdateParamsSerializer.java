package pl.grzeslowski.jsupla.protocoljava.api.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaFirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.FirmwareUpdateParams;

public interface FirmwareUpdateParamsSerializer extends
        DeviceServerSerializer<FirmwareUpdateParams, SuplaFirmwareUpdateParams> {
}
