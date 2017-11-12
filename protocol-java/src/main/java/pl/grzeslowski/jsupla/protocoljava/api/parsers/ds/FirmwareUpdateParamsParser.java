package pl.grzeslowski.jsupla.protocoljava.api.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaFirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.FirmwareUpdateParams;

public interface FirmwareUpdateParamsParser
        extends DeviceServerParser<FirmwareUpdateParams, SuplaFirmwareUpdateParams> {
}
