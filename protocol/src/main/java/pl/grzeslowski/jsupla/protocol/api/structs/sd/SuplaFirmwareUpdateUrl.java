package pl.grzeslowski.jsupla.protocol.api.structs.sd;

import pl.grzeslowski.jsupla.protocol.api.types.ProtoWithSize;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

public final class SuplaFirmwareUpdateUrl implements ProtoWithSize {
    public static final int SIZE = BYTE_SIZE + INT_SIZE + SUPLA_URL_HOST_MAXSIZE + SUPLA_URL_PATH_MAXSIZE;
    public final byte availableProtocols;
    public final byte[] host;
    public final int port;
    public final byte[] path;

    public SuplaFirmwareUpdateUrl(byte availableProtocols, byte[] host, int port, byte[] path) {
        this.availableProtocols = availableProtocols;
        this.host = checkArrayLength(host, SUPLA_URL_HOST_MAXSIZE);
        this.port = port;
        this.path = checkArrayLength(path, SUPLA_URL_PATH_MAXSIZE);
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public String toString() {
        return "SuplaFirmwareUpdateUrl{" +
                "availableProtocols=" + availableProtocols +
                ", host=" + Arrays.toString(host) +
                ", port=" + port +
                ", path=" + Arrays.toString(path) +
                '}';
    }
}
