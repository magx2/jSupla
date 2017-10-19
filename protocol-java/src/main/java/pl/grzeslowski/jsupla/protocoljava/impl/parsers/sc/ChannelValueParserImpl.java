package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelValueParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelValueParserImpl implements ChannelValueParser {
    private final pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser channelValueParser;

    public ChannelValueParserImpl(final pl.grzeslowski.jsupla.protocoljava.api.parsers.ChannelValueParser
                                          channelValueParser) {
        this.channelValueParser = requireNonNull(channelValueParser);
    }

    @Override
    public ChannelValue parse(@NotNull final SuplaChannelValue proto) {
        return new ChannelValue(
                                       proto.eol,
                                       proto.id,
                                       proto.online != 0,
                                       channelValueParser.parse(proto.value)
        );
    }
}
