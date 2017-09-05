package pl.grzeslowski.jsupla.protocoljava.api.channeltypes.ds;

import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.ChannelValue;
import pl.grzeslowski.jsupla.protocoljava.api.channelvalues.OnOff;

public interface RelayTypeChannelDecoder extends Decoder<ChannelValue> {
    @Override
    OnOff decode(byte[] bytes, int offset);

    @Override
    default OnOff decode(byte[] bytes) {
        return decode(bytes, 0);
    }
}
