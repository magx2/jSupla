package pl.grzeslowski.jsupla.protocoljava.api.entities.sdc;

import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;

import static pl.grzeslowski.jsupla.Preconditions.max;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;

public class VersionError implements ServerDeviceClientEntity {
    @PositiveOrZero
    @Max(UNSIGNED_BYTE_MAX)
    private final int serverVersionMin;
    @PositiveOrZero
    @Max(UNSIGNED_BYTE_MAX)
    private final int serverVersion;

    public VersionError(@PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int serverVersionMin,
                        @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int serverVersion) {
        this.serverVersionMin = unsignedByteSize(serverVersionMin);
        this.serverVersion = unsignedByteSize(serverVersion);
        max(serverVersionMin, serverVersion);
    }

    public int getServerVersionMin() {
        return serverVersionMin;
    }

    public int getServerVersion() {
        return serverVersion;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VersionError)) {
            return false;
        }

        final VersionError that = (VersionError) o;

        if (serverVersionMin != that.serverVersionMin) {
            return false;
        }
        return serverVersion == that.serverVersion;
    }

    @Override
    public final int hashCode() {
        int result = serverVersionMin;
        result = 31 * result + serverVersion;
        return result;
    }

    @Override
    public String toString() {
        return "VersionError{" +
            "serverVersionMin=" + serverVersionMin +
            ", serverVersion=" + serverVersion +
            '}';
    }
}
