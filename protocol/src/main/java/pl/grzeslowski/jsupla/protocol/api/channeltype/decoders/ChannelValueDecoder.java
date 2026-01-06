package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import java.util.Set;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;

public interface ChannelValueDecoder<ChannelValueT extends ChannelValue>
        extends Decoder<ChannelValueT> {
    Set<ChannelType> supportedChannelValueTypes();

    Class<ChannelValueT> getChannelValueType();
}
