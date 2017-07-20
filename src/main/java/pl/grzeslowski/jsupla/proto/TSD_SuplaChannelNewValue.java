package pl.grzeslowski.jsupla.proto;

public final class TSD_SuplaChannelNewValue {
    /**
     * unsigned
     */
    public final char channelNumber;
    public final int senderId;
    public final byte success;

    public TSD_SuplaChannelNewValue(char channelNumber, int senderId, byte success) {
        this.channelNumber = channelNumber;
        this.senderId = senderId;
        this.success = success;
    }

    @Override
    public String toString() {
        return "TSD_SuplaChannelNewValue{" +
                "channelNumber=" + channelNumber +
                ", senderId=" + senderId +
                ", success=" + success +
                '}';
    }
}
