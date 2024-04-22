package pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs;

import pl.grzeslowski.jsupla.protocol.api.structs.cs.SuplaNewValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.cs.NewValue;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.cs.NewValueSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class NewValueSerializerImpl implements NewValueSerializer {
    private final ChannelTypeEncoder channelTypeEncoder;

    public NewValueSerializerImpl(ChannelTypeEncoder channelTypeEncoder) {
        this.channelTypeEncoder = requireNonNull(channelTypeEncoder);
    }

    @Override
    public SuplaNewValue serialize(@NotNull NewValue entity) {
        return new SuplaNewValue(
            entity.getId(),
            (byte) entity.getTarget(),
            channelTypeEncoder.encode(entity.getValue())
        );
    }
}
