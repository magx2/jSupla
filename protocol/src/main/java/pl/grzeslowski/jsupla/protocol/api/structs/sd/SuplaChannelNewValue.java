package pl.grzeslowski.jsupla.protocol.api.structs.sd;

import pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.Preconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.api.calltypes.ServerDeviceCallType.SUPLA_SD_CALL_CHANNEL_SET_VALUE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public final class SuplaChannelNewValue implements ServerDevice {
    public final int senderId;
    /**
     * unsigned.
     */
    public final short channelNumber;
    /**
     * unsigned.
     */
    public final long durationMs;
    public final byte[] value;

    public SuplaChannelNewValue(int senderId, short channelNumber, long durationMs, byte[] value) {
        this.senderId = senderId;
        this.channelNumber = channelNumber;
        this.durationMs = durationMs;
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public ServerDeviceCallType callType() {
        return SUPLA_SD_CALL_CHANNEL_SET_VALUE;
    }

    @Override
    public int size() {
        return BYTE_SIZE + INT_SIZE * 2 + SUPLA_CHANNELVALUE_SIZE;
    }

    @Override
    public String toString() {
        return "SuplaChannelNewValue{" +
                "senderId=" + senderId +
                ", channelNumber=" + channelNumber +
                ", durationMs=" + durationMs +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
