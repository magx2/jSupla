package pl.grzeslowski.jsupla.protocoljava.api.entities.cs;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import static pl.grzeslowski.jsupla.Preconditions.size;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_ACCESSID_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;
import static pl.grzeslowski.jsupla.protocoljava.api.types.Entity.Version.B;

public class RegisterClientB extends RegisterClient {
    @NotNull
    @Size(min = 1, max = SUPLA_SERVER_NAME_MAXSIZE)
    private final String serverName;

    public RegisterClientB(@Positive final int accessId,
                           final @NotNull @Size(min = 1, max = SUPLA_ACCESSID_PWD_MAXSIZE) char[] accessIdPassword,
                           final @NotNull @Size(min = 1, max = SUPLA_GUID_SIZE) String guid,
                           final @NotNull @Size(min = 1, max = SUPLA_CLIENT_NAME_MAXSIZE) String name,
                           final @NotNull @Size(min = 1, max = SUPLA_SOFTVER_MAXSIZE) String softVer,
                           final @NotNull @Size(min = 1, max = SUPLA_SERVER_NAME_MAXSIZE) String serverName) {
        super(accessId, accessIdPassword, guid, name, softVer);
        this.serverName = size(serverName, 1, SUPLA_SERVER_NAME_MAXSIZE);
    }

    @Override
    public Version version() {
        return B;
    }

    public String getServerName() {
        return serverName;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterClientB)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        final RegisterClientB that = (RegisterClientB) o;

        if (!that.canEqual(this)) {
            return false;
        }

        return serverName.equals(that.serverName);
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof RegisterClientB;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + serverName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RegisterClientB{" +
                       "serverName='" + serverName + '\'' +
                       "} " + super.toString();
    }
}
