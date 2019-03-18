package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroupRelation;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroupRelation;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sc.ChannelGroupRelationParser;

import javax.validation.constraints.NotNull;

public class ChannelGroupRelationParserImpl implements ChannelGroupRelationParser {
    @Override
    public ChannelGroupRelation parse(@NotNull SuplaChannelGroupRelation proto) {
        return new ChannelGroupRelation(
                (int) proto.eol,
                proto.channelGroupId,
                proto.channelId
        );
    }
}
