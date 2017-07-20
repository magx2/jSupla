package pl.grzeslowski.jsupla.proto;

import java.util.Arrays;

public final  class TCS_SuplaChannelNewValue_B {
    public final int channelId;
    public final byte[] value;

    public TCS_SuplaChannelNewValue_B(int channelId, byte[] value) {
        this.channelId = channelId;
        this.value = value;
    }

    @Override
    public String toString() {
        return "TCS_SuplaChannelNewValue_B{" +
                "channelId=" + channelId +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
