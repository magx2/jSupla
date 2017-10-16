package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaFirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.FirmwareUpdateUrl;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.FirmwareUpdateUrlParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class FirmwareUpdateUrlParserImpl implements FirmwareUpdateUrlParser {
    private final StringParser stringParser;

    public FirmwareUpdateUrlParserImpl(final StringParser stringParser) {
        this.stringParser = requireNonNull(stringParser);
    }

    @Override
    public FirmwareUpdateUrl parse(@NotNull final SuplaFirmwareUpdateUrl proto) {
        return new FirmwareUpdateUrl(
                                            proto.availableProtocols,
                                            stringParser.parse(proto.host),
                                            proto.port,
                                            stringParser.parse(proto.path)
        );
    }
}
