package pl.grzeslowski.jsupla.protocol.structs.scd;

import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceClientCallType;

import java.util.Arrays;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_GETVERSION_RESULT;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public final class SuplaGetVersionResult implements ServerClientDevice {
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
        this.protoVersionMin = protoVersionMin;
        this.protoVersion = protoVersion;
        this.softVer = checkArrayLength(softVer, SUPLA_SOFTVER_MAXSIZE);
        if (protoVersionMin > protoVersion) {
            throw new IllegalArgumentException(
                    format("protoVersionMin (%s) need to be smaller than protoVersion (%s)!",
                            protoVersionMin, protoVersion));
        }
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
