package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.ChannelTypeEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders.ChannelTypeEncoderImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.SetActivityTimeoutSerializerImpl;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class DeviceChannelBSerializerImpl implements DeviceChannelBSerializer {
    public static final DeviceChannelBSerializerImpl INSTANCE = new DeviceChannelBSerializerImpl(
            ChannelTypeEncoderImpl.INSTANCE);
    private final ChannelTypeEncoder channelTypeEncoder;

    DeviceChannelBSerializerImpl(final ChannelTypeEncoder channelTypeEncoder) {
        this.channelTypeEncoder = requireNonNull(channelTypeEncoder);
    }

    @Override
    public SuplaDeviceChannelB serialize(@NotNull final DeviceChannelB entity) {
        return new SuplaDeviceChannelB(
                                              (short) entity.getNumber(),
                                              entity.getType(),
                                              entity.getFunction(),
                                              entity.getDefaultValue(),
                                              channelTypeEncoder.encode(entity.getValue())
        );
    }
}
