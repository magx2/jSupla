package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sd;

import pl.grzeslowski.jsupla.protocol.api.structs.sd.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sd.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sd.ChannelNewValueSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelNewValueSerializerImpl implements ChannelNewValueSerializer {
    private final ChannelTypeEncoder channelTypeEncoder;

    public ChannelNewValueSerializerImpl(final ChannelTypeEncoder channelTypeEncoder) {
        this.channelTypeEncoder = requireNonNull(channelTypeEncoder);
    }

    @Override
    public SuplaChannelNewValue serialize(@NotNull final ChannelNewValue entity) {
        return new SuplaChannelNewValue(
                                               entity.getSenderId(),
                                               (short) entity.getChannelNumber(),
                                               entity.getDurationMs(),
                                               channelTypeEncoder.encode(entity.getValue())

        );
    }
}
