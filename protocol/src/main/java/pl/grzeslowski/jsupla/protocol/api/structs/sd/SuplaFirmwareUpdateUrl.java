package pl.grzeslowski.jsupla.protocol.api.structs.sd;

import pl.grzeslowski.jsupla.Preconditions;
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
        this.port = Preconditions.size(port, 0, 65_535);
        this.path = checkArrayLength(path, SUPLA_URL_PATH_MAXSIZE);
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuplaFirmwareUpdateUrl)) {
            return false;
        }

        final SuplaFirmwareUpdateUrl that = (SuplaFirmwareUpdateUrl) o;

        if (availableProtocols != that.availableProtocols) {
            return false;
        }
        if (port != that.port) {
            return false;
        }
        if (!Arrays.equals(host, that.host)) {
            return false;
        }
        return Arrays.equals(path, that.path);
    }

    @Override
    public final int hashCode() {
        int result = (int) availableProtocols;
        result = 31 * result + Arrays.hashCode(host);
        result = 31 * result + port;
        result = 31 * result + Arrays.hashCode(path);
        return result;
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
