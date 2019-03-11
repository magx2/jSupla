package pl.grzeslowski.jsupla.protocoljava.api.entities.sc;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.UNSIGNED_BYTE_MAX;

public class RegisterClientResultB extends RegisterClientResult {
    private final int flags;

    public RegisterClientResultB(int resultCode,
                                 @Positive int clientId,
                                 @PositiveOrZero int locationCount,
                                 @PositiveOrZero int channelCount,
                                 @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) int activityTimeout,
                                 @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) int version,
                                 @PositiveOrZero @Max(UNSIGNED_BYTE_MAX) int versionMin,
                                 int flags) {
        super(resultCode, clientId, locationCount, channelCount, activityTimeout, version, versionMin);
        this.flags = flags;
    }

    public int getFlags() {
        return flags;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RegisterClientResultB that = (RegisterClientResultB) o;
        return flags == that.flags;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(super.hashCode(), flags);
    }
}
