package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.*;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.util.Set;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.OnOffValue;

class RelayTypeDecoder implements ChannelValueDecoder<OnOffValue> {
    @SuppressWarnings("deprecation")
    @Override
    public Set<ChannelType> supportedChannelValueTypes() {
        return Set.of(
                SUPLA_CHANNELTYPE_SENSORNO,
                SUPLA_CHANNELTYPE_SENSORNC,
                SUPLA_CHANNELTYPE_RELAYHFD4,
                SUPLA_CHANNELTYPE_RELAYG5LA1A,
                SUPLA_CHANNELTYPE_2XRELAYG5LA1A,
                SUPLA_CHANNELTYPE_RELAY,
                SUPLA_CHANNELTYPE_BINARYSENSOR);
    }

    @Override
    public Class<OnOffValue> getChannelValueType() {
        return OnOffValue.class;
    }

    @Override
    public OnOffValue decode(final byte[] bytes, final int offset) {
        Preconditions.sizeMin(bytes, offset);
        final short value = INSTANCE.parseUnsignedByte(bytes, offset);
        if (value == 1) {
            return OnOffValue.ON;
        }
        if (value == 0) {
            return OnOffValue.OFF;
        }
        throw new IllegalArgumentException(
                format("Don't know how to map value %s to ON/OFF!", value));
    }
}
