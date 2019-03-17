package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

import static pl.grzeslowski.jsupla.Preconditions.id;
import static pl.grzeslowski.jsupla.Preconditions.positiveOrZero;
import static pl.grzeslowski.jsupla.Preconditions.sizeMax;
import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;

@SuppressWarnings("DeprecatedIsStillUsed")
@Deprecated
public class RegisterClientResult implements ServerClientEntity {
    private final int resultCode;
    @Positive
    private final int clientId;
    @PositiveOrZero
    private final int locationCount;
    @PositiveOrZero
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
                                final @Positive int clientId,
                                final @PositiveOrZero int locationCount,
                                final @PositiveOrZero int channelCount,
                                @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int activityTimeout,
                                @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int version,
                                @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) final int versionMin) {
        this.resultCode = resultCode;
        this.clientId = id(clientId);
        this.locationCount = positiveOrZero(locationCount);
        this.channelCount = positiveOrZero(channelCount);
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterClientResult)) {
            return false;
        }
        RegisterClientResult that = (RegisterClientResult) o;
        return resultCode == that.resultCode &&
                clientId == that.clientId &&
                locationCount == that.locationCount &&
                channelCount == that.channelCount &&
                activityTimeout == that.activityTimeout &&
                version == that.version &&
                versionMin == that.versionMin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultCode, clientId, locationCount, channelCount, activityTimeout, version, versionMin);
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
