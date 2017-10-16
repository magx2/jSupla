package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelValueSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class DeviceChannelValueSerializerImpl implements DeviceChannelValueSerializer {
    private final ChannelTypeEncoder channelTypeEncoder;

    public DeviceChannelValueSerializerImpl(final ChannelTypeEncoder channelTypeEncoder) {
        this.channelTypeEncoder = requireNonNull(channelTypeEncoder);
    }

    @Override
    public SuplaDeviceChannelValue serialize(@NotNull final DeviceChannelValue entity) {
        return new SuplaDeviceChannelValue(
                                                  (short) entity.getChannelNumber(),
                                                  channelTypeEncoder.encode(entity.getValue())
        );
    }
}
