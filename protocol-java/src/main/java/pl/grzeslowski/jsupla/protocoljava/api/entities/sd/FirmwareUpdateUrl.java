package pl.grzeslowski.jsupla.protocoljava.api.entities.sd;

import pl.grzeslowski.jsupla.protocoljava.api.types.Entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import static pl.grzeslowski.jsupla.Preconditions.byteSize;
import static pl.grzeslowski.jsupla.Preconditions.size;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_HOST_MAXSIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_URL_PATH_MAXSIZE;

public class FirmwareUpdateUrl implements Entity {
    @Min(Byte.MIN_VALUE)
    @Max(Byte.MAX_VALUE)
    private final int availableProtocols;
    @NotNull
    @Size(min = 1, max = SUPLA_URL_HOST_MAXSIZE)
    private final String host;
    @PositiveOrZero
    @Max(65_535)
    private final int port;
    @NotNull
    @Size(min = 1, max = SUPLA_URL_PATH_MAXSIZE)
    private final String path;

    public FirmwareUpdateUrl(@Min(Byte.MIN_VALUE) @Max(Byte.MAX_VALUE) final int availableProtocols,
                             final @NotNull @Size(min = 1, max = SUPLA_URL_HOST_MAXSIZE) String host,
                             @PositiveOrZero @Max(65_535) final int port,
                             final @NotNull @Size(min = 1, max = SUPLA_URL_PATH_MAXSIZE) String path) {
        this.availableProtocols = byteSize(availableProtocols);
        this.host = size(host, 1, SUPLA_URL_HOST_MAXSIZE);
        this.port = size(port, 0, 65_535);
        this.path = size(path, 1, SUPLA_URL_PATH_MAXSIZE);
    }

    public int getAvailableProtocols() {
        return availableProtocols;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FirmwareUpdateUrl)) {
            return false;
        }

        final FirmwareUpdateUrl that = (FirmwareUpdateUrl) o;

        if (availableProtocols != that.availableProtocols) {
            return false;
        }
        if (port != that.port) {
            return false;
        }
        if (!host.equals(that.host)) {
            return false;
        }
        return path.equals(that.path);
    }

    @Override
    public int hashCode() {
        int result = host.hashCode();
        result = 31 * result + port;
        return result;
    }

    @Override
    public String toString() {
        return "FirmwareUpdateUrl{" +
                       "availableProtocols=" + availableProtocols +
                       ", host='" + host + '\'' +
                       ", port=" + port +
                       ", path='" + path + '\'' +
                       '}';
    }
}
