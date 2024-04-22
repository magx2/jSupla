package pl.grzeslowski.jsupla.protocoljava.api.entities.sdc;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import static pl.grzeslowski.jsupla.Preconditions.*;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_SOFTVER_MAXSIZE;

public class GetVersionResult implements ServerDeviceClientEntity {
    @PositiveOrZero
    @Max(UNSIGNED_BYTE_MAX)
    private final int protoVersionMin;
    @PositiveOrZero
    @Max(UNSIGNED_BYTE_MAX)
    private final int protoVersion;
    @NotNull
    @Size(min = 1, max = SUPLA_SOFTVER_MAXSIZE)
    private final String softVer;

    public GetVersionResult(@PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int protoVersionMin,
                            @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int protoVersion,
                            final @NotNull @Size(min = 1, max = SUPLA_SOFTVER_MAXSIZE) String softVer) {
        this.protoVersionMin = positiveOrZero(unsignedByteSize(protoVersionMin));
        this.protoVersion = positiveOrZero(unsignedByteSize(protoVersion));
        this.softVer = size(softVer, 1, SUPLA_SOFTVER_MAXSIZE);
        max(protoVersionMin, protoVersion);
    }

    public int getProtoVersionMin() {
        return protoVersionMin;
    }

    public int getProtoVersion() {
        return protoVersion;
    }

    public String getSoftVer() {
        return softVer;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GetVersionResult)) {
            return false;
        }

        final GetVersionResult that = (GetVersionResult) o;

        if (protoVersionMin != that.protoVersionMin) {
            return false;
        }
        if (protoVersion != that.protoVersion) {
            return false;
        }
        return softVer.equals(that.softVer);
    }

    @Override
    public final int hashCode() {
        int result = protoVersionMin;
        result = 31 * result + protoVersion;
        result = 31 * result + softVer.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GetVersionResult{" +
            "protoVersionMin=" + protoVersionMin +
            ", protoVersion=" + protoVersion +
            ", softVer='" + softVer + '\'' +
            '}';
    }
}
