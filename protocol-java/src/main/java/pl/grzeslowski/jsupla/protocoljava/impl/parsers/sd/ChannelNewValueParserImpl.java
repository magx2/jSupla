package pl.grzeslowski.jsupla.protocoljava.impl.parsers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds.ChannelTypeDecoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.parsers.sd.ChannelNewValueParser;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelNewValueParserImpl implements ChannelNewValueParser {
    private final ChannelTypeDecoder channelTypeDecoder;

    public ChannelNewValueParserImpl(final ChannelTypeDecoder channelTypeDecoder) {
        this.channelTypeDecoder = requireNonNull(channelTypeDecoder);
    }

    @Override
    public ChannelNewValue parse(@NotNull final SuplaChannelNewValue proto) {
        return new ChannelNewValue(
                                          proto.senderId,
                                          proto.channelNumber,
                                          proto.durationMs,
                                          channelTypeDecoder.decode(proto)
        );
    }
}
