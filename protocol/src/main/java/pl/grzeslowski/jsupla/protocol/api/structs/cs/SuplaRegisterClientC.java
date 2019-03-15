package pl.grzeslowski.jsupla.protocol.api.structs.cs;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType.SUPLA_CS_CALL_REGISTER_CLIENT_C;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.*;

/**
 * @since ver. 7
 */
public final class SuplaRegisterClientC implements ClientServer {
    public static final int SIZE = SUPLA_EMAIL_MAXSIZE
                                           + SUPLA_AUTHKEY_SIZE
                                           + SUPLA_GUID_SIZE
                                           + SUPLA_CLIENT_NAME_MAXSIZE
                                           + SUPLA_SOFTVER_MAXSIZE
                                           + SUPLA_SERVER_NAME_MAXSIZE;

    /**
     * UTF-8.
     */
    public final byte[] email;
    public final byte[] authKey;
    public final byte[] guid;
    /**
     * UTF-8.
     */
    public final byte[] name;
    public final byte[] softVer;
    public final byte[] serverName;

    public SuplaRegisterClientC(byte[] email, byte[] authKey, byte[] guid, byte[] name, byte[] softVer,
                                byte[] serverName) {
        this.email = checkArrayLength(email, SUPLA_EMAIL_MAXSIZE);
        this.authKey = checkArrayLength(authKey, SUPLA_AUTHKEY_SIZE);
        this.guid = checkArrayLength(guid, SUPLA_GUID_SIZE);
        this.name = checkArrayLength(name, SUPLA_CLIENT_NAME_MAXSIZE);
        this.softVer = checkArrayLength(softVer, SUPLA_SOFTVER_MAXSIZE);
        this.serverName = checkArrayLength(serverName, SUPLA_SERVER_NAME_MAXSIZE);
    }

    @Override
    public ClientServerCallType callType() {
        return SUPLA_CS_CALL_REGISTER_CLIENT_C;
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SuplaRegisterClientC that = (SuplaRegisterClientC) o;
        return Arrays.equals(email, that.email) &&
                       Arrays.equals(authKey, that.authKey) &&
                       Arrays.equals(guid, that.guid) &&
                       Arrays.equals(name, that.name) &&
                       Arrays.equals(softVer, that.softVer) &&
                       Arrays.equals(serverName, that.serverName);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(email);
        result = 31 * result + Arrays.hashCode(authKey);
        result = 31 * result + Arrays.hashCode(guid);
        result = 31 * result + Arrays.hashCode(name);
        result = 31 * result + Arrays.hashCode(softVer);
        result = 31 * result + Arrays.hashCode(serverName);
        return result;
    }

    @Override
    public String toString() {
        return "SuplaRegisterClientC{" +
                       "email=" + Arrays.toString(email) +
                ", authKey=[PROTECTED]" +
                       ", guid=" + Arrays.toString(guid) +
                       ", name=" + Arrays.toString(name) +
                       ", softVer=" + Arrays.toString(softVer) +
                       ", serverName=" + Arrays.toString(serverName) +
                       '}';
    }
}
