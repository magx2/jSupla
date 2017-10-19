package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannel;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

@Deprecated
public class DeviceChannelSerializerImpl implements DeviceChannelSerializer {
    private final ChannelTypeEncoder channelTypeEncoder;

    public DeviceChannelSerializerImpl(final ChannelTypeEncoder channelTypeEncoder) {
        this.channelTypeEncoder = requireNonNull(channelTypeEncoder);
    }

    @Override
    public SuplaDeviceChannel serialize(@NotNull final DeviceChannel entity) {
        return new SuplaDeviceChannel(
                                             (short) entity.getNumber(),
                                             entity.getType(),
                                             channelTypeEncoder.encode(entity.getValue())
        );
    }
}
