package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannel;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDevice;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.RegisterDeviceSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

@Deprecated
public class RegisterDeviceSerializerImpl implements RegisterDeviceSerializer {
    private final StringSerializer stringSerializer;
    private final DeviceChannelSerializer deviceChannelSerializer;

    public RegisterDeviceSerializerImpl(final StringSerializer stringSerializer,
                                        final DeviceChannelSerializer deviceChannelSerializer) {
        this.stringSerializer = requireNonNull(stringSerializer);
        this.deviceChannelSerializer = requireNonNull(deviceChannelSerializer);
    }

    @Override
    public SuplaRegisterDevice serialize(@NotNull final RegisterDevice entity) {
        return new SuplaRegisterDevice(
                                              entity.getLocationId(),
                                              stringSerializer.serializePassword(entity.getLocationPassword(), SUPLA_LOCATION_PWD_MAXSIZE),
                                              stringSerializer.serialize(entity.getGuid(), SUPLA_GUID_SIZE),
                                              stringSerializer.serialize(entity.getName(), SUPLA_DEVICE_NAME_MAXSIZE),
                                              stringSerializer.serialize(entity.getSoftVer(), SUPLA_SOFTVER_MAXSIZE),
                                              (short) entity.getChannelCount(),
                                              entity.getChannels()
                                                      .getChannels()
                                                      .stream()
                                                      .map(deviceChannelSerializer::serialize)
                                                      .toArray(SuplaDeviceChannel[]::new)
        );
    }
}
