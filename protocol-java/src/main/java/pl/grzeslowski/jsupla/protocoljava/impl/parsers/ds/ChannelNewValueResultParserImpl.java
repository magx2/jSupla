package pl.grzeslowski.jsupla.protocoljava.impl.parsers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaChannelNewValueResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.ChannelNewValueResult;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.ds.ChannelNewValueResultParser;

import javax.validation.constraints.NotNull;

public class ChannelNewValueResultParserImpl implements ChannelNewValueResultParser {
    @Override
    public ChannelNewValueResult parse(@NotNull final SuplaChannelNewValueResult proto) {
        return new ChannelNewValueResult(proto.channelNumber, proto.senderId, proto.success != 0);
    }
}
