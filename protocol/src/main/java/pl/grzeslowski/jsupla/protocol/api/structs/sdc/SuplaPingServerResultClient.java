package pl.grzeslowski.jsupla.protocol.api.structs.sdc;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType;
import pl.grzeslowski.jsupla.protocol.api.structs.Timeval;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_PING_SERVER_RESULT;

public class SuplaPingServerResultClient implements ServerDeviceClient {
    public static final int SIZE = Timeval.SIZE;
    public final Timeval timeval;

    public SuplaPingServerResultClient(final Timeval timeval) {
        this.timeval = requireNonNull(timeval);
    }

    @Override
    public ServerDeviceClientCallType callType() {
        return SUPLA_SDC_CALL_PING_SERVER_RESULT;
    }

    @Override
    public int size() {
        return SIZE;
    }

    @Override
    public String toString() {
        return "SuplaPingServerResultClient{" +
                       "timeval=" + timeval +
                       '}';
    }
}
