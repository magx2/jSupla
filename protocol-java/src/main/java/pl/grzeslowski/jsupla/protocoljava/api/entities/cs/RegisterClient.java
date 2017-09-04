package pl.grzeslowski.jsupla.protocoljava.api.entities.cs;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.Preconditions.size;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_ACCESSID_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class RegisterClient implements ClientServerEntity {
    private final int accessId;
    @NotNull
    @Size(min = 1, max = SUPLA_ACCESSID_PWD_MAXSIZE)
    private final char[] accessIdPassword;
    @NotNull
    @Size(min = 1, max = SUPLA_GUID_SIZE)
    private final String guid;
    @NotNull
    @Size(min = 1, max = SUPLA_CLIENT_NAME_MAXSIZE)
    private final String name;
    @NotNull
    @Size(min = 1, max = SUPLA_SOFTVER_MAXSIZE)
    private final String softVer;

    public RegisterClient(final int accessId,
                          final @NotNull @Size(min = 1, max = SUPLA_ACCESSID_PWD_MAXSIZE) char[] accessIdPassword,
                          final @NotNull @Size(min = 1, max = SUPLA_GUID_SIZE) String guid,
                          final @NotNull @Size(min = 1, max = SUPLA_CLIENT_NAME_MAXSIZE) String name,
                          final @NotNull @Size(min = 1, max = SUPLA_SOFTVER_MAXSIZE) String softVer) {
        this.accessId = accessId;
        this.accessIdPassword = size(accessIdPassword, 1, SUPLA_ACCESSID_PWD_MAXSIZE);
        this.guid = size(requireNonNull(guid), 1, SUPLA_GUID_SIZE);
        this.name = size(requireNonNull(name), 1, SUPLA_CLIENT_NAME_MAXSIZE);
        this.softVer = size(requireNonNull(softVer), 1, SUPLA_SOFTVER_MAXSIZE);
    }

    public int getAccessId() {
        return accessId;
    }

    public char[] getAccessIdPassword() {
        return accessIdPassword;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterClient)) {
            return false;
        }

        final RegisterClient that = (RegisterClient) o;

        if (accessId != that.accessId) {
            return false;
        }
        if (!Arrays.equals(accessIdPassword, that.accessIdPassword)) {
            return false;
        }
        if (!guid.equals(that.guid)) {
            return false;
        }
        if (!name.equals(that.name)) {
            return false;
        }
        return softVer.equals(that.softVer);
    }

    @Override
    public int hashCode() {
        int result = accessId;
        result = 31 * result + guid.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RegisterClient{" +
                       "accessId=" + accessId +
                       ", accessIdPassword=[PROTECTED]" +
                       ", guid='" + guid + '\'' +
                       ", name='" + name + '\'' +
                       ", softVer='" + softVer + '\'' +
                       '}';
    }
}
