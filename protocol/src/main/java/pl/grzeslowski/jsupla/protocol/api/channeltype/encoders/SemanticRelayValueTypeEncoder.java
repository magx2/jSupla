package pl.grzeslowski.jsupla.protocol.api.channeltype.encoders;

import pl.grzeslowski.jsupla.protocol.api.channeltype.value.AbstractOnOffValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.OnOffValue;

class SemanticRelayValueTypeEncoder implements ChannelValueEncoder<AbstractOnOffValue> {
    @Override
    public void encode(final AbstractOnOffValue value, final byte[] bytes) {
        bytes[0] = value.toCommonBase() == OnOffValue.ON ? (byte) 1 : (byte) 0;
    }
}
