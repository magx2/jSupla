package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelPackB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelPackB;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelPackBSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelPackBSerializerImpl implements ChannelPackBSerializer {
    private final ChannelBSerializer channelBSerializer;

    public ChannelPackBSerializerImpl(ChannelBSerializer channelBSerializer) {
        this.channelBSerializer = requireNonNull(channelBSerializer);
    }

    @Override
    public SuplaChannelPackB serialize(@NotNull ChannelPackB entity) {
        SuplaChannelB[] channels = entity.getChannels()
            .stream()
            .map(channelBSerializer::serialize)
            .toArray(SuplaChannelB[]::new);
        return new SuplaChannelPackB(
            entity.getChannels().size(),
            entity.getTotalLeft(),
            channels
        );
    }
}
