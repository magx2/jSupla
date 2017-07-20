package pl.grzeslowski.jsupla.proto;

import java.util.Arrays;

public final class TSD_SuplaChannelNewValue implements Proto  {
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
        this.value = value;
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
