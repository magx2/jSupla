package pl.grzeslowski.jsupla.protocoljava.api.entities.ds;

import pl.grzeslowski.jsupla.Preconditions;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_LOCATION_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;
import static pl.grzeslowski.jsupla.protocoljava.api.types.Entity.Version.C;

public class RegisterDeviceC extends RegisterDeviceB {
    @NotNull
    @Size(min = 1, max = SUPLA_SERVER_NAME_MAXSIZE)
    private final String serverName;

    public RegisterDeviceC(final int locationId,
                           final @NotNull @Size(min = 1, max = SUPLA_LOCATION_PWD_MAXSIZE) char[] locationPassword,
                           final @NotNull @Size(min = 1, max = SUPLA_GUID_SIZE) String guid,
                           final @NotNull @Size(min = 1, max = SUPLA_DEVICE_NAME_MAXSIZE) String name,
                           final @NotNull @Size(min = 1, max = SUPLA_SOFTVER_MAXSIZE) String softVer,
                           final @NotNull @Size(max = UNSIGNED_BYTE_MAX) int channelCount,
                           final @NotNull DeviceChannelsB channels,
                           final @NotNull @Size(min = 1, max = SUPLA_SERVER_NAME_MAXSIZE) String serverName) {
        super(locationId, locationPassword, guid, name, softVer, channelCount, channels);
        this.serverName = Preconditions.size(serverName, 1, SUPLA_SERVER_NAME_MAXSIZE);
    }

    @Override
    public Version version() {
        return C;
    }

    public String getServerName() {
        return serverName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterDeviceC)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        final RegisterDeviceC that = (RegisterDeviceC) o;

        return serverName.equals(that.serverName);
    }

    @Override
    public String toString() {
        return "RegisterDeviceC{" +
                       "serverName='" + serverName + '\'' +
                       "} " + super.toString();
    }
}