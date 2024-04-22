package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.Channel;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.StringParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelParserImpl implements ChannelParser {
    private final ChannelValueParser channelValueParser;
    private final StringParser stringParser;

    public ChannelParserImpl(final ChannelValueParser channelValueParser, final StringParser stringParser) {
        this.channelValueParser = requireNonNull(channelValueParser);
        this.stringParser = requireNonNull(stringParser);
    }

    @Override
    public Channel parse(@NotNull final SuplaChannel proto) {
        return new Channel(
            proto.eol,
            proto.id,
            proto.locationId,
            proto.func,
            proto.online != 0,
            channelValueParser.parse(proto.value),
            stringParser.parse(proto.caption)
        );
    }
}
