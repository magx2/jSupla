package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValuePack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelValuePack;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelValuePackParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelValueParser;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;

public class ChannelValuePackParserImpl implements ChannelValuePackParser {
    private final ChannelValueParser channelValueParser;

    public ChannelValuePackParserImpl(ChannelValueParser channelValueParser) {
        this.channelValueParser = requireNonNull(channelValueParser);
    }

    @Override
    public ChannelValuePack parse(@NotNull SuplaChannelValuePack proto) {
        List<ChannelValue> items = new ArrayList<>(proto.count);
        for (SuplaChannelValue item : proto.items) {
            @NotNull ChannelValue value = channelValueParser.parse(item);
            items.add(value);
        }
        return new ChannelValuePack(
                proto.totalLeft,
                unmodifiableList(items)
        );
    }
}
