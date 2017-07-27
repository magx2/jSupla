package pl.grzeslowski.jsupla.proto.structs.sd;

import pl.grzeslowski.jsupla.consts.CallTypes;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;
import static pl.grzeslowski.jsupla.proto.ProtoPreconditions.checkArrayLength;

public final class TSDC_SuplaGetVersionResult implements ServerDevice {
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
    public CallTypes callType() {
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
