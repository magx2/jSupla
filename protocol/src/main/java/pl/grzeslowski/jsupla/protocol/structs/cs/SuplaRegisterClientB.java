package pl.grzeslowski.jsupla.protocol.structs.cs;

import pl.grzeslowski.jsupla.protocol.calltypes.ClientServerCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.calltypes.ClientServerCallType.SUPLA_CS_CALL_REGISTER_CLIENT_B;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.*;

/**
 * @since ver 6.
 */
public final class SuplaRegisterClientB implements ClientServer {
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
        return INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE + SUPLA_GUID_SIZE + SUPLA_CLIENT_NAME_MAXSIZE +
                SUPLA_SOFTVER_MAXSIZE + SUPLA_SERVER_NAME_MAXSIZE;
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
