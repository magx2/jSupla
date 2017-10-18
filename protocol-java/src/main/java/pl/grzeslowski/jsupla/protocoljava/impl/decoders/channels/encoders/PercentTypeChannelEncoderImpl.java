package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.PercentTypeChannelEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.PercentValue;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class PercentTypeChannelEncoderImpl implements PercentTypeChannelEncoder {
    @Override
    public byte[] encode(final PercentValue percentValue) {
        final byte[] bytes = new byte[SUPLA_CHANNELVALUE_SIZE];
        bytes[0] = (byte) (10 + percentValue.getValue());
        return bytes;
    }
}
