package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.ChannelNewValueResult;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceServerEntity;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.FirmwareUpdateParams;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceC;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.ChannelNewValueResultSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelValueSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceServerEntitySerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.FirmwareUpdateParamsSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.RegisterDeviceBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.RegisterDeviceCSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.RegisterDeviceSerializer;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class DeviceServerSerializerImpl
        implements DeviceServerEntitySerializer<DeviceServerEntity, DeviceServer> {
    private final ChannelNewValueResultSerializer channelNewValueResultSerializer;
    private final RegisterDeviceSerializer registerDeviceSerializer;
    private final RegisterDeviceBSerializer registerDeviceBSerializer;
    private final RegisterDeviceCSerializer registerDeviceCSerializer;
    private final DeviceChannelValueSerializer deviceChannelValueSerializer;
    private final FirmwareUpdateParamsSerializer firmwareUpdateParamsSerializer;

    public DeviceServerSerializerImpl(final ChannelNewValueResultSerializer channelNewValueResultSerializer,
                                      final RegisterDeviceSerializer registerDeviceSerializer,
                                      final RegisterDeviceBSerializer registerDeviceBSerializer,
                                      final RegisterDeviceCSerializer registerDeviceCSerializer,
                                      final DeviceChannelValueSerializer deviceChannelValueSerializer,
                                      final FirmwareUpdateParamsSerializer firmwareUpdateParamsSerializer) {
        this.channelNewValueResultSerializer = requireNonNull(channelNewValueResultSerializer);
        this.registerDeviceSerializer = requireNonNull(registerDeviceSerializer);
        this.registerDeviceBSerializer = requireNonNull(registerDeviceBSerializer);
        this.registerDeviceCSerializer = requireNonNull(registerDeviceCSerializer);
        this.deviceChannelValueSerializer = requireNonNull(deviceChannelValueSerializer);
        this.firmwareUpdateParamsSerializer = requireNonNull(firmwareUpdateParamsSerializer);
    }

    @Override
    public DeviceServer serialize(@NotNull final DeviceServerEntity entity) {
        if (entity instanceof ChannelNewValueResult) {
            return channelNewValueResultSerializer.serialize((ChannelNewValueResult) entity);
        } else if (entity instanceof RegisterDeviceC) {
            return registerDeviceCSerializer.serialize((RegisterDeviceC) entity);
        } else if (entity instanceof RegisterDeviceB) {
            return registerDeviceBSerializer.serialize((RegisterDeviceB) entity);
        } else if (entity instanceof RegisterDevice) {
            return registerDeviceSerializer.serialize((RegisterDevice) entity);
        } else if (entity instanceof FirmwareUpdateParams) {
            return firmwareUpdateParamsSerializer.serialize((FirmwareUpdateParams) entity);
        } else if (entity instanceof DeviceChannelValue) {
            return deviceChannelValueSerializer.serialize((DeviceChannelValue) entity);
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to serializer! %s",
                entity.getClass(), entity));
    }
}
