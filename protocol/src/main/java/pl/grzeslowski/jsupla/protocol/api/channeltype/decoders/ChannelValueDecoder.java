package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import java.util.Set;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.channeltype.ChannelDescription;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;

public interface ChannelValueDecoder<ChannelValueT extends ChannelValue>
        extends Decoder<ChannelValueT> {
    Set<ChannelType> supportedChannelValueTypes();

    Class<ChannelValueT> getChannelValueType();

    default ChannelValue decode(byte[] bytes, int offset, ChannelDescription description) {
        return decode(bytes, offset);
    }

    default Class<? extends ChannelValue> getChannelValueType(ChannelDescription description) {
        return getChannelValueType();
    }

    default ChannelValue decode(byte[] bytes, ChannelDescription description) {
        return decode(bytes, 0, description);
    }
}
