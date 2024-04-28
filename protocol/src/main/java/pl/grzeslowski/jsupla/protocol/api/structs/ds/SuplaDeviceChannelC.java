package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;

/**
 * @since v10.
 */
public final class SuplaDeviceChannelC implements DeviceServer {
    public static final int SIZE = -1;

    /**
     * unsigned char
     */
//    public final short number;
//    public final int type;

    // todo https://github.com/SUPLA/supla-core/blob/master/supla-common/proto.h#L934
    @Override
    public DeviceServerCallType callType() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
