package pl.grzeslowski.jsupla.protocoljava.api.entities.sd;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class FirmwareUpdateUrlResult implements ServerDeviceEntity {
    private final boolean exists;
    @NotNull
    @Valid
    private final FirmwareUpdateUrl firmwareUpdateUrl;

    public FirmwareUpdateUrlResult(final boolean exists,
                                   final @NotNull @Valid FirmwareUpdateUrl firmwareUpdateUrl) {
        this.exists = exists;
        this.firmwareUpdateUrl = requireNonNull(firmwareUpdateUrl);
    }

    public boolean isExists() {
        return exists;
    }

    public FirmwareUpdateUrl getFirmwareUpdateUrl() {
        return firmwareUpdateUrl;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FirmwareUpdateUrlResult)) {
            return false;
        }

        final FirmwareUpdateUrlResult that = (FirmwareUpdateUrlResult) o;

        if (exists != that.exists) {
            return false;
        }
        return firmwareUpdateUrl.equals(that.firmwareUpdateUrl);
    }

    @Override
    public final int hashCode() {
        int result = (exists ? 1 : 0);
        result = 31 * result + firmwareUpdateUrl.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FirmwareUpdateUrlResult{" +
                       "exists=" + exists +
                       ", firmwareUpdateUrl=" + firmwareUpdateUrl +
                       '}';
    }
}
