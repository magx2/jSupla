package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders.ChannelTypeEncoderImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.SetActivityTimeoutSerializerImpl;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class DeviceChannelValueSerializerImpl implements DeviceChannelValueSerializer {
    public static final DeviceChannelValueSerializerImpl INSTANCE = new DeviceChannelValueSerializerImpl(
            ChannelTypeEncoderImpl.INSTANCE);
    private final ChannelTypeEncoder channelTypeEncoder;

    DeviceChannelValueSerializerImpl(final ChannelTypeEncoder channelTypeEncoder) {
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
