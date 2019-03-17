package pl.grzeslowski.jsupla.protocoljava.api.entities.ds;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;
import static pl.grzeslowski.jsupla.Preconditions.notEmpty;
import static pl.grzeslowski.jsupla.Preconditions.sizeMin;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_AUTHKEY_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_DEVICE_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_EMAIL_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class RegisterDeviceD implements DeviceServerEntity {
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
    @Size(min = 1)
    @NotNull
    private final List<DeviceChannelB> channels;

    public RegisterDeviceD(
        @Size(min = 1, max = SUPLA_EMAIL_MAXSIZE) @NotNull String email,
        @Size(min = 1, max = SUPLA_AUTHKEY_SIZE) @NotNull String authKey,
        @Size(min = 1, max = SUPLA_GUID_SIZE) @NotNull String guid,
        @Size(min = 1, max = SUPLA_DEVICE_NAME_MAXSIZE) @NotNull String name,
        @Size(min = 1, max = SUPLA_SOFTVER_MAXSIZE) @NotNull String softVer,
        @Size(min = 1, max = SUPLA_SERVER_NAME_MAXSIZE) @NotNull String serverName,
        @Size(min = 1) @NotNull List<DeviceChannelB> channels) {
        this.email = notEmpty(email);
        this.authKey = notEmpty(authKey);
        this.guid = notEmpty(guid);
        this.name = notEmpty(name);
        this.softVer = notEmpty(softVer);
        this.serverName = notEmpty(serverName);
        this.channels = unmodifiableList(sizeMin(channels, 1));
    }

    public String getEmail() {
        return email;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }

    public String getSoftVer() {
        return softVer;
    }

    public String getServerName() {
        return serverName;
    }

    public List<DeviceChannelB> getChannels() {
        return channels;
    }

    @Override
    public boolean equals(Object o) {
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
    public int hashCode() {
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
