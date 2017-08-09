package pl.grzeslowski.jsupla.protocol.structs.scd;

import pl.grzeslowski.jsupla.protocol.call_types.ServerDeviceClientCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.ProtoPreconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public final class TSDC_SuplaGetVersionResult implements ServerClientDevice {
    /**
     * unsigned
     */
    public final byte protoVersionMin;
    /**
     * unsigned
     */
    public final byte protoVersion;
    public final byte[] softVer;

    public TSDC_SuplaGetVersionResult(byte protoVersionMin, byte protoVersion, byte[] softVer) {
        this.protoVersionMin = protoVersionMin;
        this.protoVersion = protoVersion;
        this.softVer = checkArrayLength(softVer, SUPLA_SOFTVER_MAXSIZE);
    }

    @Override
    public ServerDeviceClientCallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return BYTE_SIZE*2 + SUPLA_SOFTVER_MAXSIZE;
    }

    @Override
    public String toString() {
        return "TSDC_SuplaGetVersionResult{" +
                "protoVersionMin=" + protoVersionMin +
                ", protoVersion=" + protoVersion +
                ", softVer=" + Arrays.toString(softVer) +
                '}';
    }
}
