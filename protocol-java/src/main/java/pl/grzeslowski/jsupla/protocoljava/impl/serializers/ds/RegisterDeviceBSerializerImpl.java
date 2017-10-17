package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceB;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.RegisterDeviceBSerializer;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

@Deprecated
public class RegisterDeviceBSerializerImpl implements RegisterDeviceBSerializer {
    private final StringSerializer stringSerializer;
    private final DeviceChannelBSerializer deviceChannelBSerializer;

    public RegisterDeviceBSerializerImpl(final StringSerializer stringSerializer,
                                         final DeviceChannelBSerializer deviceChannelBSerializer) {
        this.stringSerializer = requireNonNull(stringSerializer);
        this.deviceChannelBSerializer = requireNonNull(deviceChannelBSerializer);
    }

    @Override
    public SuplaRegisterDeviceB serialize(@NotNull final RegisterDeviceB entity) {
        return new SuplaRegisterDeviceB(
                                               entity.getLocationId(),
                                               stringSerializer.serializePassword(entity.getLocationPassword(),
                                                       SUPLA_LOCATION_PWD_MAXSIZE),
                                               stringSerializer.serialize(entity.getGuid(), SUPLA_GUID_SIZE),
                                               stringSerializer.serialize(entity.getName(), SUPLA_DEVICE_NAME_MAXSIZE),
                                               stringSerializer.serialize(entity.getSoftVer(), SUPLA_SOFTVER_MAXSIZE),
                                               (short) entity.getChannelCount(),
                                               entity.getChannels()
                                                       .getChannels()
                                                       .stream()
                                                       .map(deviceChannelBSerializer::serialize)
                                                       .toArray(SuplaDeviceChannelB[]::new)
        );
    }
}
