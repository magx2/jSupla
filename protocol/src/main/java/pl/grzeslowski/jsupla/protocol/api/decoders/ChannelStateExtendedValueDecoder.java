package pl.grzeslowski.jsupla.protocol.api.decoders;

import pl.grzeslowski.jsupla.protocol.api.structs.ChannelStateExtendedValue;

public class ChannelStateExtendedValueDecoder implements ProtoWithSizeDecoder<ChannelStateExtendedValue> {
    public static final ChannelStateExtendedValueDecoder INSTANCE = new ChannelStateExtendedValueDecoder();

    @Override
    public ChannelStateExtendedValue decode(byte[] bytes, int offset) {
        throw new UnsupportedOperationException("ChannelStateExtendedValueDecoder.decode(bytes, offset)");
    }
}
