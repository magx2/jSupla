package pl.grzeslowski.jsupla.proto;

import java.util.Arrays;

@Deprecated
public final  class TCS_SuplaChannelNewValue  implements Proto {
    public final int channelId;
    public final byte[] value;

    public TCS_SuplaChannelNewValue(int channelId, byte[] value) {
        this.channelId = channelId;
        this.value = value;
    }

    @Override
    public String toString() {
        return "TCS_SuplaChannelNewValue{" +
                "channelId=" + channelId +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
