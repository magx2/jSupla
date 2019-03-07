package pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.ChannelNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.ChannelNewValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders.ChannelTypeEncoderImpl;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

@Deprecated
public class ChannelNewValueSerializerImpl implements ChannelNewValueSerializer {
    public static final ChannelNewValueSerializerImpl INSTANCE = new ChannelNewValueSerializerImpl(
            ChannelTypeEncoderImpl.INSTANCE);
    private final ChannelTypeEncoder channelTypeEncoder;

    ChannelNewValueSerializerImpl(final ChannelTypeEncoder channelTypeEncoder) {
        this.channelTypeEncoder = requireNonNull(channelTypeEncoder);
    }

    @Override
    public SuplaChannelNewValue serialize(@NotNull final ChannelNewValue entity) {
        return new SuplaChannelNewValue(
                                               (byte) entity.getChannelId(),
                                               channelTypeEncoder.encode(entity.getValue())
        );
    }
}
