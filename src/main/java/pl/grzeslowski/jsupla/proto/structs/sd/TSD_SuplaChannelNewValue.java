package pl.grzeslowski.jsupla.proto.structs.sd;

import pl.grzeslowski.jsupla.consts.CallTypes;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;
import static pl.grzeslowski.jsupla.proto.ProtoPreconditions.checkArrayLength;

public final class TSD_SuplaChannelNewValue implements ServerDevice {
    public final int senderId;
    /**
     * unsigned
     */
    public final byte channelNumber;
    /**
     * unsigned
     */
    public final int durationMs;
    public final byte[] value;

    public TSD_SuplaChannelNewValue(int senderId, byte channelNumber, int durationMs, byte[] value) {
        this.senderId = senderId;
        this.channelNumber = channelNumber;
        this.durationMs = durationMs;
        this.value = checkArrayLength(value, SUPLA_CHANNELVALUE_SIZE);
    }

    @Override
    public CallTypes callType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return BYTE_SIZE + INT_SIZE*2 + SUPLA_CHANNELVALUE_SIZE;
    }

    @Override
    public String toString() {
        return "TSD_SuplaChannelNewValue{" +
                "senderId=" + senderId +
                ", channelNumber=" + channelNumber +
                ", durationMs=" + durationMs +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
