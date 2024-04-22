package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroupRelation;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroupRelationPack;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelGroupRelationPackParser;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelGroupRelationParser;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;

public class ChannelGroupRelationPackParserImpl implements ChannelGroupRelationPackParser {
    private final ChannelGroupRelationParser channelGroupRelationParser;

    public ChannelGroupRelationPackParserImpl(ChannelGroupRelationParser channelGroupRelationParser) {
        this.channelGroupRelationParser = requireNonNull(channelGroupRelationParser);
    }

    @Override
    public ChannelGroupRelationPack parse(@NotNull SuplaChannelGroupRelationPack proto) {
        List<ChannelGroupRelation> items = new ArrayList<>(proto.items.length);
        for (SuplaChannelGroupRelation item : proto.items) {
            @NotNull ChannelGroupRelation channelGroupRelation = channelGroupRelationParser.parse(item);
            items.add(channelGroupRelation);
        }
        return new ChannelGroupRelationPack(
            proto.totalLeft,
            unmodifiableList(items)
        );
    }
}
