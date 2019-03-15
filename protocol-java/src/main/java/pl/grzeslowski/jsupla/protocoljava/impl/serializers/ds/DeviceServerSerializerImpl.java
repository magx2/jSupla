package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.DeviceServer;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.*;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.*;

import javax.validation.constraints.NotNull;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class DeviceServerSerializerImpl
        implements DeviceServerSerializer<DeviceServerEntity, DeviceServer> {
    private final ChannelNewValueResultSerializer channelNewValueResultSerializer;
    private final RegisterDeviceSerializer registerDeviceSerializer;
    private final RegisterDeviceBSerializer registerDeviceBSerializer;
    private final RegisterDeviceCSerializer registerDeviceCSerializer;
    private final DeviceChannelValueSerializer deviceChannelValueSerializer;
    private final FirmwareUpdateParamsSerializer firmwareUpdateParamsSerializer;
    private final RegisterDeviceDSerializer registerDeviceDSerializer;

    public DeviceServerSerializerImpl(final ChannelNewValueResultSerializer channelNewValueResultSerializer,
                                      final RegisterDeviceSerializer registerDeviceSerializer,
                                      final RegisterDeviceBSerializer registerDeviceBSerializer,
                                      final RegisterDeviceCSerializer registerDeviceCSerializer,
                                      final DeviceChannelValueSerializer deviceChannelValueSerializer,
                                      final FirmwareUpdateParamsSerializer firmwareUpdateParamsSerializer,
                                      final RegisterDeviceDSerializer registerDeviceDSerializer) {
        this.channelNewValueResultSerializer = requireNonNull(channelNewValueResultSerializer);
        this.registerDeviceSerializer = requireNonNull(registerDeviceSerializer);
        this.registerDeviceBSerializer = requireNonNull(registerDeviceBSerializer);
        this.registerDeviceCSerializer = requireNonNull(registerDeviceCSerializer);
        this.deviceChannelValueSerializer = requireNonNull(deviceChannelValueSerializer);
        this.firmwareUpdateParamsSerializer = requireNonNull(firmwareUpdateParamsSerializer);
        this.registerDeviceDSerializer = registerDeviceDSerializer;
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
        } else if (entity instanceof RegisterDeviceD) {
            return registerDeviceDSerializer.serialize((RegisterDeviceD) entity);
        }
        throw new IllegalArgumentException(format("Don't know how to map this class \"%s\" to serializer! %s",
                entity.getClass(), entity));
    }
}
