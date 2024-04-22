package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroup;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroup;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelGroupParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelGroupParserImpl implements ChannelGroupParser {
    private final StringParser stringParser;

    public ChannelGroupParserImpl(StringParser stringParser) {
        this.stringParser = requireNonNull(stringParser);
    }

    @Override
    public ChannelGroup parse(@NotNull SuplaChannelGroup proto) {
        return new ChannelGroup(
            proto.eol,
            proto.id,
            proto.locationId,
            proto.func,
            proto.altIcon,
            proto.flags,
            stringParser.parse(proto.caption)
        );
    }
}
