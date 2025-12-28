package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import pl.grzeslowski.jsupla.protocol.api.channeltype.value.PercentValue;

public class PercentageTypeEncoder {

    public byte[] encode(PercentValue percentValue) {
        var bytes = new byte[8];
        bytes[0] = (byte) percentValue.value();
        return bytes;
    }
}
