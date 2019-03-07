package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPack;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelPack;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelPackSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.SetActivityTimeoutSerializerImpl;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelPackSerializerImpl implements ChannelPackSerializer {
    public static final ChannelPackSerializerImpl INSTANCE = new ChannelPackSerializerImpl(
            ChannelSerializerImpl.INSTANCE);
    private final ChannelSerializer channelSerializer;

    ChannelPackSerializerImpl(final ChannelSerializer channelSerializer) {
        this.channelSerializer = requireNonNull(channelSerializer);
    }

    @Override
    public SuplaChannelPack serialize(@NotNull final ChannelPack entity) {
        return new SuplaChannelPack(
                                           entity.getChannels().size(),
                                           entity.getTotalLeft(),
                                           entity.getChannels()
                                                   .stream()
                                                   .map(channelSerializer::serialize)
                                                   .toArray(SuplaChannel[]::new)
        );
    }
}
