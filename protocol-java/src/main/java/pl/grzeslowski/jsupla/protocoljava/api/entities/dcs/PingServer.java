package pl.grzeslowski.jsupla.protocoljava.api.entities.dcs;

import pl.grzeslowski.jsupla.protocoljava.api.entities.Timeval;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class PingServer implements DeviceClientServerEntity {
    @NotNull
    @Valid
    private final Timeval timeval;

    public PingServer(final @NotNull @Valid Timeval timeval) {
        this.timeval = requireNonNull(timeval);
    }

    public Timeval getTimeval() {
        return timeval;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PingServer)) {
            return false;
        }

        final PingServer that = (PingServer) o;

        return timeval.equals(that.timeval);
    }

    @Override
    public final int hashCode() {
        return timeval.hashCode();
    }

    @Override
    public String toString() {
        return "PingServer{" +
                       "timeval=" + timeval +
                       '}';
    }
}
