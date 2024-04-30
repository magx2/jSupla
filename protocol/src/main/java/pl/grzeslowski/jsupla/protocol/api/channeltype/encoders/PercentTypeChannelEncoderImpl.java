package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;


import pl.grzeslowski.jsupla.protocol.api.channeltype.value.PercentValue;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

class PercentTypeChannelEncoderImpl {
    public byte[] encode(final PercentValue percentValue) {
        final byte[] bytes = new byte[SUPLA_CHANNELVALUE_SIZE];
        bytes[0] = (byte) (10 + percentValue.getValue());
        return bytes;
    }
}
