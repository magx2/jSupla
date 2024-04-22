package pl.grzeslowski.jsupla.protocoljava.impl.serializers;

import pl.grzeslowski.jsupla.protocol.api.structs.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ChannelValueSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelValueSerializerImpl implements ChannelValueSerializer {
    private final ChannelTypeEncoder channelTypeEncoder;

    public ChannelValueSerializerImpl(final ChannelTypeEncoder channelTypeEncoder) {
        this.channelTypeEncoder = requireNonNull(channelTypeEncoder);
    }

    @Override
    public SuplaChannelValue serialize(@NotNull final ChannelValue entity) {
        final byte[] value = channelTypeEncoder.encode(entity.getValue());
        return entity.getSubValue()
            .map(subValue -> new SuplaChannelValue(value, channelTypeEncoder.encode(subValue)))
            .orElseGet(() -> new SuplaChannelValue(value, null));

    }
}
