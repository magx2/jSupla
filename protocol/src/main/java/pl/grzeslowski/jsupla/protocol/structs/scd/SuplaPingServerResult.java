package pl.grzeslowski.jsupla.protocol.structs.scd;

import pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceClientCallType;

import static pl.grzeslowski.jsupla.protocol.calltypes.ServerDeviceClientCallType.SUPLA_SDC_CALL_PING_SERVER_RESULT;

public class SuplaPingServerResult implements ServerClientDevice {
    // TODO implement
    //    typedef struct {
    //
    //        // server -> device|client
    //
    //        struct timeval now;
    //
    //    }TSDC_SuplaPingServerResult;


    @Override
    public ServerDeviceClientCallType callType() {
        return SUPLA_SDC_CALL_PING_SERVER_RESULT;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }
}
