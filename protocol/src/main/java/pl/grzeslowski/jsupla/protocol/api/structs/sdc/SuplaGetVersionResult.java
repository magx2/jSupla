package pl.grzeslowski.jsupla.protocol.api.structs.sdc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.Preconditions.max;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_GETVERSION_RESULT;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public final class SuplaGetVersionResult implements ServerDeviceClient {
    /**
     * unsigned.
     */
    public final short protoVersionMin;
    /**
     * unsigned.
     */
    public final short protoVersion;
    public final byte[] softVer;

    public SuplaGetVersionResult(short protoVersionMin, short protoVersion, byte[] softVer) {
        this.protoVersionMin = max(protoVersionMin, protoVersion);
        this.protoVersion = protoVersion;
        this.softVer = checkArrayLength(softVer, SUPLA_SOFTVER_MAXSIZE);
    }

    @Override
    public ServerDeviceClientCallType callType() {
        return SUPLA_SDC_CALL_GETVERSION_RESULT;
    }

    @Override
    public int size() {
        return BYTE_SIZE * 2 + SUPLA_SOFTVER_MAXSIZE;
    }

    @Override
    public String toString() {
        return "SuplaGetVersionResult{" +
                "protoVersionMin=" + protoVersionMin +
                ", protoVersion=" + protoVersion +
                ", softVer=" + Arrays.toString(softVer) +
                '}';
    }
}
