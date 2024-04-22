package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelPack;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelPackParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelParser;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class ChannelPackParserImpl implements ChannelPackParser {
    private final ChannelParser channelParser;

    public ChannelPackParserImpl(final ChannelParser channelParser) {
        this.channelParser = requireNonNull(channelParser);
    }

    @Override
    public ChannelPack parse(@NotNull final SuplaChannelPack proto) {
        return new ChannelPack(
            proto.totalLeft,
            Arrays.stream(proto.channels)
                .map(channelParser::parse)
                .collect(Collectors.toList())
        );
    }
}
