package pl.grzeslowski.jsupla.proto;

import java.util.Arrays;

public  final class TDS_SuplaDeviceChannelValue {
    /**
     * unsigned
     */
    public final byte channelNumber;
    public final byte[] value;

    public TDS_SuplaDeviceChannelValue(byte channelNumber, byte[] value) {
        this.channelNumber = channelNumber;
        this.value = value;
    }

    @Override
    public String toString() {
        return "TDS_SuplaDeviceChannelValue{" +
                "channelNumber=" + channelNumber +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}
