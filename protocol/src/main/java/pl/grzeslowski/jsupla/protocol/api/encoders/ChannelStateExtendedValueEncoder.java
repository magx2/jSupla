package pl.grzeslowski.jsupla.protocol.api.encoders;

import pl.grzeslowski.jsupla.protocol.api.structs.ChannelStateExtendedValue;

public class ChannelStateExtendedValueEncoder implements Encoder<ChannelStateExtendedValue> {
    public static ChannelStateExtendedValueEncoder INSTANCE = new ChannelStateExtendedValueEncoder();

    @Override
    public byte[] encode(ChannelStateExtendedValue proto) {
        throw new java.lang.UnsupportedOperationException("Do not support encoding structs with unions!");
    }
}
