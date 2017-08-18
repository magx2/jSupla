package pl.grzeslowski.jsupla.protocol.api.structs.cs;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ClientServerCallType.SUPLA_CS_CALL_REGISTER_CLIENT;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_ACCESSID_PWD_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CLIENT_NAME_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_GUID_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

@Deprecated
public final class SuplaRegisterClient implements ClientServer {
    public static final int SIZE = INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE + SUPLA_GUID_SIZE + SUPLA_CLIENT_NAME_MAXSIZE
                                           + SUPLA_SOFTVER_MAXSIZE;
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

    public SuplaRegisterClient(int accessId, byte[] accessIdPwd, byte[] guid, byte[] name, byte[] softVer) {
        this.accessId = accessId;
        this.accessIdPwd = checkArrayLength(accessIdPwd, SUPLA_ACCESSID_PWD_MAXSIZE);
        this.guid = checkArrayLength(guid, SUPLA_GUID_SIZE);
        this.name = checkArrayLength(name, SUPLA_CLIENT_NAME_MAXSIZE);
        this.softVer = checkArrayLength(softVer, SUPLA_SOFTVER_MAXSIZE);
    }


    @Override
    public ClientServerCallType callType() {
        return SUPLA_CS_CALL_REGISTER_CLIENT;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public String toString() {
        return "SuplaRegisterClient{" +
                       "accessId=" + accessId +
                       ", accessIdPwd=" + Arrays.toString(accessIdPwd) +
                       ", guid=" + Arrays.toString(guid) +
                       ", name=" + Arrays.toString(name) +
                       ", softVer=" + Arrays.toString(softVer) +
                       '}';
    }
}
