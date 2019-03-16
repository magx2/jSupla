package pl.grzeslowski.jsupla.protocoljava.impl.serializers.ds;

import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaDeviceChannelB;
import pl.grzeslowski.jsupla.protocol.api.structs.ds.SuplaRegisterDeviceD;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.DeviceChannelB;
import pl.grzeslowski.jsupla.protocoljava.api.entities.ds.RegisterDeviceD;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.StringSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.DeviceChannelBSerializer;
import pl.grzeslowski.jsupla.protocoljava.api.serializers.ds.RegisterDeviceDSerializer;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

public class RegisterDeviceDSerializerImpl implements RegisterDeviceDSerializer {
    private final StringSerializer stringSerializer;
    private final DeviceChannelBSerializer deviceChannelBSerializer;

    public RegisterDeviceDSerializerImpl(StringSerializer stringSerializer, DeviceChannelBSerializer deviceChannelBSerializer) {
        this.stringSerializer = requireNonNull(stringSerializer);
        this.deviceChannelBSerializer = requireNonNull(deviceChannelBSerializer);
    }

    @Override
    public SuplaRegisterDeviceD serialize(@NotNull RegisterDeviceD entity) {
        SuplaDeviceChannelB[] channels = entity.getChannels()
                .stream()
                .map((Function<DeviceChannelB, Object>) deviceChannelBSerializer::serialize)
                .toArray(SuplaDeviceChannelB[]::new);
        return new SuplaRegisterDeviceD(
                stringSerializer.serialize(entity.getEmail(), SUPLA_EMAIL_MAXSIZE),
                stringSerializer.serialize(entity.getAuthKey(), SUPLA_AUTHKEY_SIZE),
                stringSerializer.serializeHexString(entity.getGuid()),
                stringSerializer.serialize(entity.getName(), SUPLA_DEVICE_NAME_MAXSIZE),
                stringSerializer.serialize(entity.getSoftVer(), SUPLA_SOFTVER_MAXSIZE),
                stringSerializer.serialize(entity.getServerName(), SUPLA_SERVER_NAME_MAXSIZE),
                (short) channels.length,
                channels
        );
    }
}
