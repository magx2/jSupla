package pl.grzeslowski.jsupla.server.entities.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static pl.grzeslowski.jsupla.Preconditions.*;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.*;

public class DeviceRegisterEvent implements Request {
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
    @NotNull
    @Size(max = SUPLA_CHANNELMAXCOUNT)
    private final List<DeviceChannel> channels;

    public DeviceRegisterEvent(int locationId, String locationPassword, String guid, String name, String softVersion,
                               List<? extends DeviceChannel> channels) {
        this.locationId = min(locationId, 0);
        this.locationPassword = size(locationPassword, 1, SUPLA_LOCATION_PWD_MAXSIZE);
        this.guid = size(guid, 1, SUPLA_GUID_HEXSIZE);
        this.name = size(name, 1, SUPLA_DEVICE_NAME_MAXSIZE);
        this.softVersion = size(softVersion, 1, SUPLA_SOFTVER_MAXSIZE);
        this.channels = unmodifiableList(new ArrayList<>(sizeMax(channels, SUPLA_CHANNELMAXCOUNT)));
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

    public List<? extends DeviceChannel> getChannels() {
        return channels;
    }

    @Override
    public String toString() {
        return "DeviceRegisterEvent{" +
                "locationId=" + locationId +
                ", locationPassword='" + locationPassword + '\'' +
                ", guid='" + guid + '\'' +
                ", name='" + name + '\'' +
                ", softVersion=" + softVersion +
                ", channels=" + channels +
                '}';
    }
}
