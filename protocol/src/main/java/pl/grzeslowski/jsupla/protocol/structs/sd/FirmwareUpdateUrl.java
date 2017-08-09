package pl.grzeslowski.jsupla.protocol.structs.sd;

import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.ProtoPreconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

public final class FirmwareUpdateUrl implements ServerDevice {
    public final byte availableProtocols;
    public final byte[] host;
    public final int port;
    public final byte[] path;

    public FirmwareUpdateUrl(byte availableProtocols, byte[] host, int port, byte[] path) {
        this.availableProtocols = availableProtocols;
        this.host = checkArrayLength(host, SUPLA_URL_HOST_MAXSIZE);
        this.port = port;
        this.path = checkArrayLength(path, SUPLA_URL_PATH_MAXSIZE);
    }

    @Override
    public ServerDeviceCallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return BYTE_SIZE + INT_SIZE + SUPLA_URL_HOST_MAXSIZE + SUPLA_URL_PATH_MAXSIZE;
    }

    @Override
    public String toString() {
        return "FirmwareUpdateUrl{" +
                "availableProtocols=" + availableProtocols +
                ", host=" + Arrays.toString(host) +
                ", port=" + port +
                ", path=" + Arrays.toString(path) +
                '}';
    }
}
