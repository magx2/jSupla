package pl.grzeslowski.jsupla.protocol.structs.scd;

import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceClientCallType;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;

public final class SuplaVersionError implements ServerClientDevice {
    /**
     * unsigned.
     */
    public final short serverVersionMin;
    /**
     * unsigned.
     */
    public final short serverVersion;

    public SuplaVersionError(short serverVersionMin, short serverVersion) {
        this.serverVersionMin = serverVersionMin;
        this.serverVersion = serverVersion;
    }

    @Override
    public ServerDeviceClientCallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return BYTE_SIZE * 2;
    }

    @Override
    public String toString() {
        return "SuplaVersionError{" +
                "serverVersionMin=" + serverVersionMin +
                ", serverVersion=" + serverVersion +
                '}';
    }
}
