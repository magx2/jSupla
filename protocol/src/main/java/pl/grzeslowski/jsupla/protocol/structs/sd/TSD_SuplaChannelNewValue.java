package pl.grzeslowski.jsupla.protocol.structs.sd;

import pl.grzeslowski.jsupla.protocol.call_types.ServerDeviceCallType;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.ProtoPreconditions.checkArrayLength;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

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
    public ServerDeviceCallType callType() {
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
