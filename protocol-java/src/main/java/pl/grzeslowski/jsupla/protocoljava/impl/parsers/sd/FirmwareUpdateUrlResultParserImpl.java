package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrlResult;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlResultParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class FirmwareUpdateUrlResultParserImpl implements FirmwareUpdateUrlResultParser {
    private final FirmwareUpdateUrlParser firmwareUpdateUrlParser;

    public FirmwareUpdateUrlResultParserImpl(final FirmwareUpdateUrlParser firmwareUpdateUrlParser) {
        this.firmwareUpdateUrlParser = requireNonNull(firmwareUpdateUrlParser);
    }

    @Override
    public FirmwareUpdateUrlResult parse(@NotNull final SuplaFirmwareUpdateUrlResult proto) {
        return new FirmwareUpdateUrlResult(proto.exists != 0, firmwareUpdateUrlParser.parse(proto.url));
    }
}
