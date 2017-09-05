package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;

import static pl.grzeslowski.jsupla.Preconditions.sizeMax;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;

public class RegisterClientResult implements ServerClientEntity {
    private final int resultCode;
    private final int clientId;
    private final int locationCount;
    private final int channelCount;
    @PositiveOrZero
    @Max(UNSIGNED_BYTE_MAX)
    private final int activityTimeout;
    @PositiveOrZero
    @Max(UNSIGNED_BYTE_MAX)
    private final int version;
    @PositiveOrZero
    @Max(UNSIGNED_BYTE_MAX)
    private final int versionMin;

    public RegisterClientResult(final int resultCode,
                                final int clientId,
                                final int locationCount,
                                final int channelCount,
                                @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int activityTimeout,
                                @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int version,
                                @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int versionMin) {
        this.resultCode = resultCode;
        this.clientId = clientId;
        this.locationCount = locationCount;
        this.channelCount = channelCount;
        this.activityTimeout = unsignedByteSize(activityTimeout);
        this.version = unsignedByteSize(version);
        this.versionMin = unsignedByteSize(versionMin);
        sizeMax(versionMin, version);
    }

    public int getResultCode() {
        return resultCode;
    }

    public int getClientId() {
        return clientId;
    }

    public int getLocationCount() {
        return locationCount;
    }

    public int getChannelCount() {
        return channelCount;
    }

    public int getActivityTimeout() {
        return activityTimeout;
    }

    public int getVersion() {
        return version;
    }

    public int getVersionMin() {
        return versionMin;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterClientResult)) {
            return false;
        }

        final RegisterClientResult that = (RegisterClientResult) o;

        if (resultCode != that.resultCode) {
            return false;
        }
        if (clientId != that.clientId) {
            return false;
        }
        if (locationCount != that.locationCount) {
            return false;
        }
        if (channelCount != that.channelCount) {
            return false;
        }
        if (activityTimeout != that.activityTimeout) {
            return false;
        }
        if (version != that.version) {
            return false;
        }
        return versionMin == that.versionMin;
    }

    @Override
    public int hashCode() {
        int result = resultCode;
        result = 31 * result + clientId;
        return result;
    }

    @Override
    public String toString() {
        return "RegisterClientResult{" +
                       "resultCode=" + resultCode +
                       ", clientId=" + clientId +
                       ", locationCount=" + locationCount +
                       ", channelCount=" + channelCount +
                       ", activityTimeout=" + activityTimeout +
                       ", version=" + version +
                       ", versionMin=" + versionMin +
                       '}';
    }
}
