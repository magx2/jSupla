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
    public String toString() {
        return "SuplaPingServer{" +
                       "timeval=" + timeval +
                       '}';
    }
}
