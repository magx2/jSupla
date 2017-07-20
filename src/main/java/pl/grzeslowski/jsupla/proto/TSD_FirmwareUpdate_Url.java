package pl.grzeslowski.jsupla.proto;

import java.util.Arrays;

public final class TSD_FirmwareUpdate_Url implements Proto  {
    public final byte avaiableProtocols;
    public final byte[] host;
    public final int port;
    public final byte[] path;

    public TSD_FirmwareUpdate_Url(byte avaiableProtocols, byte[] host, int port, byte[] path) {
        this.avaiableProtocols = avaiableProtocols;
        this.host = host;
        this.port = port;
        this.path = path;
    }

    @Override
    public String toString() {
        return "TSD_FirmwareUpdate_Url{" +
                "avaiableProtocols=" + avaiableProtocols +
                ", host=" + Arrays.toString(host) +
                ", port=" + port +
                ", path=" + Arrays.toString(path) +
                '}';
    }
}
