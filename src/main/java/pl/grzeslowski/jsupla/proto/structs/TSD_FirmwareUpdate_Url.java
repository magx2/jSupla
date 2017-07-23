package pl.grzeslowski.jsupla.proto.structs;

import pl.grzeslowski.jsupla.proto.Proto;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;
import static pl.grzeslowski.jsupla.proto.ProtoPreconditions.checkArrayLength;

public final class TSD_FirmwareUpdate_Url implements Proto {
    public final byte availableProtocols;
    public final byte[] host;
    public final int port;
    public final byte[] path;

    public TSD_FirmwareUpdate_Url(byte availableProtocols, byte[] host, int port, byte[] path) {
        this.availableProtocols = availableProtocols;
        this.host = checkArrayLength(host, SUPLA_URL_HOST_MAXSIZE );
        this.port = port;
        this.path = checkArrayLength(path, SUPLA_URL_PATH_MAXSIZE );
    }

    @Override
    public String toString() {
        return "TSD_FirmwareUpdate_Url{" +
                "availableProtocols=" + availableProtocols +
                ", host=" + Arrays.toString(host) +
                ", port=" + port +
                ", path=" + Arrays.toString(path) +
                '}';
    }
}
