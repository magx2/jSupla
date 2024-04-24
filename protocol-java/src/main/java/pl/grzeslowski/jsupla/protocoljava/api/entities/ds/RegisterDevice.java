package pl.grzeslowski.jsupla.protocoljava.api.entities.ds;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocoljava.api.types.traits.RegisterDeviceTrait;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

public class RegisterDevice implements DeviceServerEntity, RegisterDeviceTrait {
    private final int locationId;
    @NotNull
    @Size(min = 1, max = SUPLA_LOCATION_PWD_MAXSIZE)
    private final char[] locationPassword;
    @NotNull
    @Size(min = 1, max = SUPLA_GUID_SIZE)
    private final String guid;
    @NotNull
    @Size(min = 1, max = SUPLA_DEVICE_NAME_MAXSIZE)
    private final String name;
    @NotNull
    @Size(min = 1, max = SUPLA_SOFTVER_MAXSIZE)
    private final String softVer;
    @NotNull
    private final DeviceChannels channels;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public RegisterDevice(final int locationId,
                          final @NotNull @Size(min = 1, max = SUPLA_LOCATION_PWD_MAXSIZE) char[] locationPassword,
                          final @NotNull @Size(min = 1, max = SUPLA_GUID_SIZE) String guid,
                          final @NotNull @Size(min = 1, max = SUPLA_DEVICE_NAME_MAXSIZE) String name,
                          final @NotNull @Size(min = 1, max = SUPLA_SOFTVER_MAXSIZE) String softVer,
                          final @NotNull DeviceChannels channels) {
        this.locationId = locationId;
        this.locationPassword = Preconditions.size(locationPassword, 1, SUPLA_LOCATION_PWD_MAXSIZE);
        this.guid = Preconditions.size(guid, 1, SUPLA_GUID_SIZE * 2);
        this.name = Preconditions.size(name, 1, SUPLA_DEVICE_NAME_MAXSIZE);
        this.softVer = Preconditions.size(softVer, 1, SUPLA_SOFTVER_MAXSIZE);
        this.channels = requireNonNull(channels);
    }

    public int getLocationId() {
        return locationId;
    }

    public char[] getLocationPassword() {
        return locationPassword;
    }

    @Override
    public String getGuid() {
        return guid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSoftVer() {
        return softVer;
    }

    @Override
    public DeviceChannels getChannels() {
        return channels;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterDevice)) {
            return false;
        }

        final RegisterDevice that = (RegisterDevice) o;

        if (!that.canEqual(this)) {
            return false;
        }
        if (locationId != that.locationId) {
            return false;
        }
        if (!Arrays.equals(locationPassword, that.locationPassword)) {
            return false;
        }
        if (!guid.equals(that.guid)) {
            return false;
        }
        if (!name.equals(that.name)) {
            return false;
        }
        if (!softVer.equals(that.softVer)) {
            return false;
        }
        return channels.equals(that.channels);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RegisterDevice;
    }

    @Override
    public int hashCode() {
        return locationId;
    }

    @Override
    public String toString() {
        return "RegisterDevice{" +
            "locationId=" + locationId +
            ", locationPassword=[PROTECTED]" +
            ", guid='" + guid + '\'' +
            ", name='" + name + '\'' +
            ", softVer='" + softVer + '\'' +
            ", channels=" + channels +
            '}';
    }
}
