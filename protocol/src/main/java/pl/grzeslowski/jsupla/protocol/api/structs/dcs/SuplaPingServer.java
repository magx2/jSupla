package pl.grzeslowski.jsupla.protocol.api.structs.dcs;

import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceClientServerCallType;

public class SuplaPingServer implements DeviceClientServer {
    // TODO
    //    typedef struct {
    //
    //        // device|client -> server
    //
    //        struct timeval now;
    //
    //    }TDCS_SuplaPingServer;

    @Override
    public DeviceClientServerCallType callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }
}
