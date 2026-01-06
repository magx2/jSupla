package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import pl.grzeslowski.jsupla.protocol.api.channeltype.value.PercentValue;

class PercentTypeEncoder implements ChannelValueEncoder<PercentValue> {
    @Override
    public void encode(PercentValue value, byte[] bytes) {
        bytes[0] = (byte) value.value();
    }
}
