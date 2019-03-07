package pl.grzeslowski.jsupla.protocoljava.impl.decoders.channels.encoders;

import pl.grzeslowski.jsupla.protocoljava.api.channels.encoders.RelayTypeChannelEncoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.OnOff;
import pl.grzeslowski.jsupla.protocoljava.impl.serializers.cs.ChannelNewValueBSerializerImpl;

import static pl.grzeslowski.jsupla.protocol.api.consts.ProtoConsts.SUPLA_CHANNELVALUE_SIZE;

public class RelayTypeChannelEncoderImpl implements RelayTypeChannelEncoder {
    public static final RelayTypeChannelEncoderImpl INSTANCE = new RelayTypeChannelEncoderImpl();
    @Override
    public byte[] encode(final OnOff onOff) {
        final byte[] bytes = new byte[SUPLA_CHANNELVALUE_SIZE];
        switch (onOff) {
            case ON:
                bytes[0] = 1;
                break;
            case OFF:
                bytes[0] = 0;
                break;
            default:
                throw new UnsupportedOperationException("Don't know this OnOff type " + onOff + "!");
        }
        return bytes;
    }
}
