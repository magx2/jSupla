package pl.grzeslowski.jsupla.protocoljava.impl.serializers.sc;

import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelGroup;
import pl.grzeslowski.jsupla.protocoljava.api.entities.sc.ChannelGroup;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.sc.ChannelGroupSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class ChannelGroupSerializerImpl implements ChannelGroupSerializer {
    private final StringSerializer stringSerializer;

    public ChannelGroupSerializerImpl(StringSerializer stringSerializer) {
        this.stringSerializer = requireNonNull(stringSerializer);
    }

    @Override
    public SuplaChannelGroup serialize(@NotNull ChannelGroup entity) {
        byte[] caption = stringSerializer.serialize(entity.getCaption());
        return new SuplaChannelGroup(
            (byte) entity.getEol(),
            entity.getId(),
            entity.getLocationId(),
            entity.getFunction(),
            entity.getAltIcon(),
            entity.getFlags(),
            caption.length,
            caption
        );
    }
}
