package pl.grzeslowski.jsupla.protocol.structs.cs;

import pl.grzeslowski.jsupla.protocol.consts.CallType;
import pl.grzeslowski.jsupla.protocol.structs.ds.DeviceServer;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.ProtoPreconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.*;

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
