package pl.grzeslowski.jsupla.protocol.api.decoders.channelvalues;

import pl.grzeslowski.jsupla.protocol.api.channelvalues.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;

public interface ChannelValueDecoder<T extends ChannelValue> extends Decoder<T> {
}
