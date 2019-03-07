package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceC;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceC;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.RegisterDeviceCSerializer;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.StringSerializerImpl;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.dcs.SetActivityTimeoutSerializerImpl;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class RegisterDeviceCSerializerImpl implements RegisterDeviceCSerializer {
    public static final RegisterDeviceCSerializerImpl INSTANCE = new RegisterDeviceCSerializerImpl(
            StringSerializerImpl.INSTANCE,
            DeviceChannelBSerializerImpl.INSTANCE);
    private final StringSerializer stringSerializer;
    private final DeviceChannelBSerializer deviceChannelBSerializer;

    RegisterDeviceCSerializerImpl(final StringSerializer stringSerializer,
                                         final DeviceChannelBSerializer deviceChannelBSerializer) {
        this.stringSerializer = requireNonNull(stringSerializer);
        this.deviceChannelBSerializer = requireNonNull(deviceChannelBSerializer);
    }

    @Override
    public SuplaRegisterDeviceC serialize(@NotNull final RegisterDeviceC entity) {
        return new SuplaRegisterDeviceC(
                                               entity.getLocationId(),
                                               stringSerializer.serializePassword(entity.getLocationPassword(),
                                                       SUPLA_LOCATION_PWD_MAXSIZE),
                                               stringSerializer.serialize(entity.getGuid(), SUPLA_GUID_SIZE),
                                               stringSerializer.serialize(entity.getName(), SUPLA_DEVICE_NAME_MAXSIZE),
                                               stringSerializer.serialize(entity.getSoftVer(), SUPLA_SOFTVER_MAXSIZE),
                                               stringSerializer.serialize(entity.getServerName(),
                                                       SUPLA_SERVER_NAME_MAXSIZE),
                                               (short) entity.getChannelCount(),
                                               entity.getChannels()
                                                       .getChannels()
                                                       .stream()
                                                       .map(deviceChannelBSerializer::serialize)
                                                       .toArray(SuplaDeviceChannelB[]::new)
        );
    }
}
