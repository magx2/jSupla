package pl.grzeslowski.jsupla.protocol.api.structs.dcs;

import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType.SUPLA_DCS_CALL_PING_SERVER;

public final class SuplaPingServer implements DeviceClientServer {
    public static final int SIZE = SuplaTimeval.SIZE;
    public final SuplaTimeval timeval;

    public SuplaPingServer(final SuplaTimeval timeval) {
        this.timeval = requireNonNull(timeval);
    }

    @Override
    public DeviceClientServerCallType callType() {
        return SUPLA_DCS_CALL_PING_SERVER;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof SuplaPingServer)) return false;

        final SuplaPingServer that = (SuplaPingServer) o;

        return timeval.equals(that.timeval);
    }

    @Override
    public int hashCode() {
        return timeval.hashCode();
    }

    @Override
    public String toString() {
        return "SuplaPingServer{" +
                       "timeval=" + timeval +
                       '}';
    }
}
