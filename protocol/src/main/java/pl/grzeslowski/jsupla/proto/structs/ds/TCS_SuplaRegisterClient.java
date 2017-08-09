package pl.grzeslowski.jsupla.proto.structs.ds;

import pl.grzeslowski.jsupla.proto.consts.CallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.proto.ProtoPreconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.proto.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.proto.consts.ProtoConsts.*;

@Deprecated
public final class TCS_SuplaRegisterClient implements DeviceServer {
    public final int accessId;
    /**
     * UTF-8
     */
    public final byte[] accessIdPwd;
    public final byte[] guid;
    /**
     * UTF-8
     */
    public final byte[] name;
    public final byte[] softVer;

    public TCS_SuplaRegisterClient(int accessId, byte[] accessIdPwd, byte[] guid, byte[] name, byte[] softVer) {
        this.accessId = accessId;
        this.accessIdPwd = checkArrayLength(accessIdPwd, SUPLA_ACCESSID_PWD_MAXSIZE);
        this.guid = checkArrayLength(guid, SUPLA_GUID_SIZE);
        this.name = checkArrayLength(name, SUPLA_CLIENT_NAME_MAXSIZE);
        this.softVer = checkArrayLength(softVer, SUPLA_SOFTVER_MAXSIZE);
    }


    @Override
    public CallType callType() {
        throw new UnsupportedOperationException();
    }
    @Override
    public int size() {
        return INT_SIZE + SUPLA_ACCESSID_PWD_MAXSIZE+SUPLA_GUID_SIZE+SUPLA_CLIENT_NAME_MAXSIZE+SUPLA_SOFTVER_MAXSIZE;
    }

    @Override
    public String toString() {
        return "TCS_SuplaRegisterClient{" +
                "accessId=" + accessId +
                ", accessIdPwd=" + Arrays.toString(accessIdPwd) +
                ", guid=" + Arrays.toString(guid) +
                ", name=" + Arrays.toString(name) +
                ", softVer=" + Arrays.toString(softVer) +
                '}';
    }
}
