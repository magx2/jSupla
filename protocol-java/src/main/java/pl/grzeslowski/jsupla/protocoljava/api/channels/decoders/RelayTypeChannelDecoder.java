package pl.grzeslowski.jsupla.protocoljava.api.channels.decoders;

import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.OnOff;

public interface RelayTypeChannelDecoder extends Decoder<ChannelValue> {
    @Override
    OnOff decode(byte[] bytes, int offset);

    @Override
    default OnOff decode(byte[] bytes) {
        return decode(bytes, 0);
    }
}
