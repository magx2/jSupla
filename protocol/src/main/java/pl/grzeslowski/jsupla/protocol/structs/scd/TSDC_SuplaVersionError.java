package pl.grzeslowski.jsupla.protocol.structs.scd;

import pl.grzeslowski.jsupla.protocol.call_types.ServerDeviceClientCallType;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;

public final class TSDC_SuplaVersionError implements ServerDeviceClient {
    /**
     * unsigned
     */
    public final byte serverVersionMin;
    /**
     * unsigned
     */
    public final byte serverVersion;

    public TSDC_SuplaVersionError(byte serverVersionMin, byte serverVersion) {
        this.serverVersionMin = serverVersionMin;
        this.serverVersion = serverVersion;
    }

    @Override
    public ServerDeviceClientCallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return BYTE_SIZE *2;
    }

    @Override
    public String toString() {
        return "TSDC_SuplaVersionError{" +
                "serverVersionMin=" + serverVersionMin +
                ", serverVersion=" + serverVersion +
                '}';
    }
}
