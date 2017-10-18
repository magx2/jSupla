package pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValueB;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.ChannelNewValueBSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelNewValueBSerializerImpl implements ChannelNewValueBSerializer {
    private final ChannelTypeEncoder channelTypeEncoder;

    public ChannelNewValueBSerializerImpl(final ChannelTypeEncoder channelTypeEncoder) {
        this.channelTypeEncoder = requireNonNull(channelTypeEncoder);
    }

    @Override
    public SuplaChannelNewValueB serialize(@NotNull final ChannelNewValueB entity) {
        return new SuplaChannelNewValueB(
                                                entity.getChannelId(),
                                                channelTypeEncoder.encode(entity.getValue())
        );
    }
}
