package pl.grzeslowski.jsupla.protocol.structs.ds;

import pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType;

import static pl.grzeslowski.jsupla.protocol.calltypes.DeviceServerCallType.SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.*;

public final class SuplaChannelNewValueResult implements DeviceServer {
    /**
     * unsigned.
     */
    public final short channelNumber;
    public final int senderId;
    public final byte success;

    public SuplaChannelNewValueResult(short channelNumber, int senderId, byte success) {
        this.channelNumber = channelNumber;
        this.senderId = senderId;
        this.success = success;
    }

    @Override
    public DeviceServerCallType callType() {
        return SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED;
    }

    @Override
    public int size() {
        return BYTE_SIZE + INT_SIZE + CHAR_SIZE;
    }

    @Override
    public String toString() {
        return "SuplaChannelNewValueResult{" +
                "channelNumber=" + channelNumber +
                ", senderId=" + senderId +
                ", success=" + success +
                '}';
    }
}
