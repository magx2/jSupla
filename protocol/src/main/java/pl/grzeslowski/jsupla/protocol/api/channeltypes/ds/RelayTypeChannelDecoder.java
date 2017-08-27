package pl.grzeslowski.jsupla.protocol.api.channeltypes.ds;

import pl.grzeslowski.jsupla.protocol.api.channelvalues.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.channelvalues.OnOff;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;

public interface RelayTypeChannelDecoder extends Decoder<ChannelValue> {
    @Override
    OnOff decode(byte[] bytes, int offset);

    @Override
    default OnOff decode(byte[] bytes) {
        return decode(bytes, 0);
    }
}
