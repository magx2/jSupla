package pl.grzeslowski.jsupla.protocoljava.api.entities.sdc;

import pl.grzeslowski.jsupla.protocoljava.api.entities.Timeval;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class PingServerResultClient implements ServerDeviceClientEntity {
    @NotNull
    @Valid
    private final Timeval timeval;

    public PingServerResultClient(final @NotNull @Valid Timeval timeval) {
        this.timeval = requireNonNull(timeval);
    }

    public Timeval getTimeval() {
        return timeval;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PingServerResultClient)) {
            return false;
        }

        final PingServerResultClient that = (PingServerResultClient) o;

        return timeval.equals(that.timeval);
    }

    @Override
    public int hashCode() {
        return timeval.hashCode();
    }

    @Override
    public String toString() {
        return "PingServerResultClient{" +
                       "timeval=" + timeval +
                       '}';
    }
}
