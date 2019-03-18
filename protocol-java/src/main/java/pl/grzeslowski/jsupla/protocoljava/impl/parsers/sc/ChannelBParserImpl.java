package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelBParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelBParserImpl implements ChannelBParser {
    private final ChannelValueParser channelValueParser;
    private final StringParser stringParser;

    public ChannelBParserImpl(ChannelValueParser channelValueParser, StringParser stringParser) {
        this.channelValueParser = requireNonNull(channelValueParser);
        this.stringParser = requireNonNull(stringParser);
    }

    @Override
    public ChannelB parse(@NotNull SuplaChannelB proto) {
        return new ChannelB(
                proto.eol,
                proto.id,
                proto.locationId,
                proto.func,
                proto.online != 0,
                channelValueParser.parse(proto.value),
                stringParser.parse(proto.caption),
                proto.altIcon,
                proto.flags,
                proto.protocolVersion
        );
    }
}
