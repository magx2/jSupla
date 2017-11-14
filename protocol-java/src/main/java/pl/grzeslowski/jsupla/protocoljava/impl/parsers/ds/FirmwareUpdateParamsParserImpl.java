package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaFirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.FirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.FirmwareUpdateParamsParser;

import javax.validation.constraints.NotNull;

public class FirmwareUpdateParamsParserImpl implements FirmwareUpdateParamsParser {
    @Override
    public FirmwareUpdateParams parse(@NotNull final SuplaFirmwareUpdateParams proto) {
        return new FirmwareUpdateParams(
                                               proto.platform,
                                               proto.param1,
                                               proto.param2,
                                               proto.param3,
                                               proto.param4
        );
    }
}
