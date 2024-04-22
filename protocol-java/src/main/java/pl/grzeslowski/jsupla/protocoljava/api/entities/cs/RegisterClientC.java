package pl.grzeslowski.jsupla.protocoljava.api.entities.cs;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

import static pl.grzeslowski.jsupla.Preconditions.size;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;
import static pl.grzeslowski.jsupla.protocoljava.api.types.Entity.Version.C;

public class RegisterClientC implements ClientServerEntity {
    @NotNull
    @Size(min = 1, max = SUPLA_EMAIL_MAXSIZE)
    private final String email;
    @NotNull
    @Size(min = 1, max = SUPLA_AUTHKEY_SIZE)
    private final String authKey;
    @NotNull
    @Size(min = 1, max = SUPLA_GUID_SIZE)
    private final String guid;
    @NotNull
    @Size(min = 1, max = SUPLA_CLIENT_NAME_MAXSIZE)
    private final String name;
    @NotNull
    @Size(min = 1, max = SUPLA_SOFTVER_MAXSIZE)
    private final String softVer;
    @NotNull
    @Size(min = 1, max = SUPLA_SERVER_NAME_MAXSIZE)
    private final String serverName;

    public RegisterClientC(
        @NotNull @Size(min = 1, max = SUPLA_EMAIL_MAXSIZE) String email,
        @NotNull @Size(min = 1, max = SUPLA_AUTHKEY_SIZE) String authKey,
        @NotNull @Size(min = 1, max = SUPLA_GUID_SIZE) String guid,
        @NotNull @Size(min = 1, max = SUPLA_CLIENT_NAME_MAXSIZE) String name,
        @NotNull @Size(min = 1, max = SUPLA_SOFTVER_MAXSIZE) String softVer,
        @NotNull @Size(min = 1, max = SUPLA_SERVER_NAME_MAXSIZE) String serverName) {
        this.email = size(email, 1, SUPLA_EMAIL_MAXSIZE);
        this.authKey = size(authKey, 1, SUPLA_AUTHKEY_SIZE);
        this.guid = size(guid, 1, SUPLA_GUID_SIZE);
        this.name = size(name, 1, SUPLA_CLIENT_NAME_MAXSIZE);
        this.softVer = size(softVer, 1, SUPLA_SOFTVER_MAXSIZE);
        this.serverName = size(serverName, 1, SUPLA_SERVER_NAME_MAXSIZE);
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

    @Override
    public Version version() {
        return C;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterClientC)) {
            return false;
        }
        RegisterClientC that = (RegisterClientC) o;
        return Objects.equals(email, that.email) &&
            Objects.equals(authKey, that.authKey) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(name, that.name) &&
            Objects.equals(softVer, that.softVer) &&
            Objects.equals(serverName, that.serverName);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(email, authKey, guid, name, softVer, serverName);
    }

    @Override
    public String toString() {
        return "RegisterClientC{" +
            "email='" + email + '\'' +
            ", authKey='[PROTECTED]'" +
            ", guid='" + guid + '\'' +
            ", name='" + name + '\'' +
            ", softVer='" + softVer + '\'' +
            ", serverName='" + serverName + '\'' +
            '}';
    }
}
