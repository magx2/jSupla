package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPackB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelPackB;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelBParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelPackBParser;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class ChannelPackBParserImpl implements ChannelPackBParser {
    private final ChannelBParser channelBParser;

    public ChannelPackBParserImpl(ChannelBParser channelBParser) {
        this.channelBParser = requireNonNull(channelBParser);
    }

    @Override
    public ChannelPackB parse(@NotNull SuplaChannelPackB proto) {
        List<ChannelB> channels = new ArrayList<>(proto.count);
        for (SuplaChannelB channel : proto.channels) {
            @NotNull ChannelB parse = channelBParser.parse(channel);
            channels.add(parse);
        }
        return new ChannelPackB(
            proto.totalLeft,
            channels
        );
    }
}
