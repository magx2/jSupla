package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ChannelValue;

public interface ChannelValueEncoder<ChannelValueT extends ChannelValue> {
    default byte[] encode(ChannelValueT value) {
        var bytes = new byte[SUPLA_CHANNELVALUE_SIZE];
        encode(value, bytes);
        return bytes;
    }

    void encode(ChannelValueT value, byte[] bytes);
}
