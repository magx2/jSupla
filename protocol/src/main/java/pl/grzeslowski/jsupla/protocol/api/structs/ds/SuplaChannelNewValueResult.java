package pl.grzeslowski.jsupla.protocol.api.structs.ds;

import pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType;

import static pl.grzeslowski.jsupla.Preconditions.unsignedByteSize;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.DeviceServerCallType.SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.CHAR_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaChannelNewValueResult implements DeviceServer {
    public static final int SIZE = BYTE_SIZE + INT_SIZE + CHAR_SIZE;
    /**
     * unsigned.
     */
    public final short channelNumber;
    public final int senderId;
    public final byte success;

    public SuplaChannelNewValueResult(short channelNumber, int senderId, byte success) {
        this.channelNumber = unsignedByteSize(channelNumber);
        this.senderId = senderId;
        this.success = success;
    }

    @Override
    public DeviceServerCallType callType() {
        return SUPLA_DS_CALL_DEVICE_CHANNEL_VALUE_CHANGED;
    }

    @Override
    public int size() {
        return SIZE;
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
