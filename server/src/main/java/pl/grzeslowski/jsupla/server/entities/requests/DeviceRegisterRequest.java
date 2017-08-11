package pl.grzeslowski.jsupla.server.entities.requests;

import pl.grzeslowski.jsupla.server.entities.misc.DeviceChannels;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static pl.grzeslowski.jsupla.Preconditions.min;
import static pl.grzeslowski.jsupla.Preconditions.size;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.*;

public class DeviceRegisterRequest implements Request {
    @Min(0)
    private final int locationId;
    @NotNull
    @Size(min = 1, max = SUPLA_LOCATION_PWD_MAXSIZE)
    private final String locationPassword;
    @NotNull
    @Size(min = 1, max = SUPLA_GUID_SIZE)
    private final String guid;
    @NotNull
    @Size(min = 1, max = SUPLA_DEVICE_NAME_MAXSIZE)
    private final String name;
    @NotNull
    @Size(min = 1, max = SUPLA_SOFTVER_MAXSIZE)
    private final String softVersion;
    private final DeviceChannels channels;

    public DeviceRegisterRequest(int locationId, String locationPassword, String guid, String name, String softVersion,
                                 DeviceChannels channels) {
        this.locationId = min(locationId, 0);
        this.locationPassword = size(locationPassword, 1, SUPLA_LOCATION_PWD_MAXSIZE);
        this.guid = size(guid, 1, SUPLA_GUID_HEXSIZE);
        this.name = size(name, 1, SUPLA_DEVICE_NAME_MAXSIZE);
        this.softVersion = size(softVersion, 1, SUPLA_SOFTVER_MAXSIZE);
        this.channels = channels;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getLocationPassword() {
        return locationPassword;
    }

    public String getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }

    public String getSoftVersion() {
        return softVersion;
    }

    public DeviceChannels getChannels() {
        return channels;
    }

    @Override
    public String toString() {
        return "DeviceRegisterRequest{" +
                "locationId=" + locationId +
                ", locationPassword='" + locationPassword + '\'' +
                ", guid='" + guid + '\'' +
                ", name='" + name + '\'' +
                ", softVersion=" + softVersion +
                ", channels=" + channels +
                '}';
    }
}
