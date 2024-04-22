package pl.grzeslowski.jsupla.protocoljava.api.entities.ds;

import pl.grzeslowski.jsupla.protocoljava.api.types.traits.RegisterDeviceTrait;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.Preconditions.notEmpty;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

public class RegisterDeviceD implements DeviceServerEntity, RegisterDeviceTrait {
    @Size(min = 1, max = SUPLA_EMAIL_MAXSIZE)
    @NotNull
    private final String email;
    @Size(min = 1, max = SUPLA_AUTHKEY_SIZE)
    @NotNull
    private final String authKey;
    @Size(min = 1, max = SUPLA_GUID_SIZE)
    @NotNull
    private final String guid;
    @Size(min = 1, max = SUPLA_DEVICE_NAME_MAXSIZE)
    @NotNull
    private final String name;
    @Size(min = 1, max = SUPLA_SOFTVER_MAXSIZE)
    @NotNull
    private final String softVer;
    @Size(min = 1, max = SUPLA_SERVER_NAME_MAXSIZE)
    @NotNull
    private final String serverName;
    @NotNull
    private final DeviceChannelsB channels;

    public RegisterDeviceD(
        @Size(min = 1, max = SUPLA_EMAIL_MAXSIZE) @NotNull String email,
        @Size(min = 1, max = SUPLA_AUTHKEY_SIZE) @NotNull String authKey,
        @Size(min = 1, max = SUPLA_GUID_SIZE) @NotNull String guid,
        @Size(min = 1, max = SUPLA_DEVICE_NAME_MAXSIZE) @NotNull String name,
        @Size(min = 1, max = SUPLA_SOFTVER_MAXSIZE) @NotNull String softVer,
        @Size(min = 1, max = SUPLA_SERVER_NAME_MAXSIZE) @NotNull String serverName,
        @Size(min = 1) @NotNull DeviceChannelsB channels) {
        this.email = notEmpty(email);
        this.authKey = notEmpty(authKey);
        this.guid = notEmpty(guid);
        this.name = notEmpty(name);
        this.softVer = notEmpty(softVer);
        this.serverName = notEmpty(serverName);
        this.channels = requireNonNull(channels);
    }

    public String getEmail() {
        return email;
    }

    public String getAuthKey() {
        return authKey;
    }

    @Override
    public String getGuid() {
        return guid;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getSoftVer() {
        return softVer;
    }

    public String getServerName() {
        return serverName;
    }

    @Override
    public DeviceChannelsB getChannels() {
        return channels;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterDeviceD)) {
            return false;
        }
        RegisterDeviceD that = (RegisterDeviceD) o;
        return Objects.equals(email, that.email) &&
            Objects.equals(authKey, that.authKey) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(name, that.name) &&
            Objects.equals(softVer, that.softVer) &&
            Objects.equals(serverName, that.serverName) &&
            Objects.equals(channels, that.channels);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(email, authKey, guid, name, softVer, serverName, channels);
    }

    @Override
    public String toString() {
        return "RegisterDeviceD{" +
            "email='" + email + '\'' +
            ", authKey=[PROTECTED]" +
            ", guid='" + guid + '\'' +
            ", name='" + name + '\'' +
            ", softVer='" + softVer + '\'' +
            ", serverName='" + serverName + '\'' +
            ", channels=" + channels +
            '}';
    }
}
