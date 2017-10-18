package pl.grzeslowski.jsupla.protocoljava.api.decoders.channelvalues;

import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;
import pl.grzeslowski.jsupla.protocoljava.api.channels.values.ChannelValue;

public interface ChannelValueDecoder<T extends ChannelValue> extends Decoder<T> {
}
