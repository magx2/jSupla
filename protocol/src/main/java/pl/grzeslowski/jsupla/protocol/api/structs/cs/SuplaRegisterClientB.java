package pl.grzeslowski.jsupla.protocol.api.structs.cs;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType.SUPLA_CS_CALL_REGISTER_CLIENT_B;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_ACCESSID_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SERVER_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

/**
 * @since ver 6.
 */
@Deprecated
public final class SuplaRegisterClientB implements ClientServer {
    public static final int SIZE = INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE + SUPLA_GUID_SIZE + SUPLA_CLIENT_NAME_MAXSIZE +
                   SUPLA_SOFTVER_MAXSIZE + SUPLA_SERVER_NAME_MAXSIZE;
    public final int accessId;
    /**
     * UTF-8.
     */
    public final byte[] accessIdPwd;
    public final byte[] guid;
    /**
     * UTF-8.
     */
    public final byte[] name;
    public final byte[] softVer;
    public final byte[] serverName;

    public SuplaRegisterClientB(int accessId,
                                byte[] accessIdPwd,
                                byte[] guid,
                                byte[] name,
                                byte[] softVer,
                                byte[] serverName) {
        this.accessId = accessId;
        this.accessIdPwd = checkArrayLength(accessIdPwd, SUPLA_ACCESSID_PWD_MAXSIZE);
        this.guid = checkArrayLength(guid, SUPLA_GUID_SIZE);
        this.name = checkArrayLength(name, SUPLA_CLIENT_NAME_MAXSIZE);
        this.softVer = checkArrayLength(softVer, SUPLA_SOFTVER_MAXSIZE);
        this.serverName = checkArrayLength(serverName, SUPLA_SERVER_NAME_MAXSIZE);
    }

    @Override
    public ClientServerCallType callType() {
        return SUPLA_CS_CALL_REGISTER_CLIENT_B;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuplaRegisterClientB)) {
            {
                return false;
            }
        }

        final SuplaRegisterClientB that = (SuplaRegisterClientB) o;

        if (accessId != that.accessId) {
            {
                return false;
            }
        }
        if (!Arrays.equals(accessIdPwd, that.accessIdPwd)) {
            {
                return false;
            }
        }
        if (!Arrays.equals(guid, that.guid)) {
            return false;
        }
        if (!Arrays.equals(name, that.name)) {
            return false;
        }
        if (!Arrays.equals(softVer, that.softVer)) {
            return false;
        }
        return Arrays.equals(serverName, that.serverName);
    }

    @Override
    public final int hashCode() {
        int result = accessId;
        result = 31 * result + Arrays.hashCode(accessIdPwd);
        result = 31 * result + Arrays.hashCode(guid);
        result = 31 * result + Arrays.hashCode(name);
        result = 31 * result + Arrays.hashCode(softVer);
        result = 31 * result + Arrays.hashCode(serverName);
        return result;
    }

    @Override
    public String toString() {
        return "SuplaRegisterClientB{" +
                "accessId=" + accessId +
                ", accessIdPwd=" + Arrays.toString(accessIdPwd) +
                ", guid=" + Arrays.toString(guid) +
                ", name=" + Arrays.toString(name) +
                ", softVer=" + Arrays.toString(softVer) +
                ", serverName=" + Arrays.toString(serverName) +
                '}';
    }
}
