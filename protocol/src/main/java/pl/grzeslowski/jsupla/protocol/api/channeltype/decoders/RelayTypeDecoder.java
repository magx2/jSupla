package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.api.ChannelType.*;
import static pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder.INSTANCE;

import java.util.Set;
import pl.grzeslowski.jsupla.protocol.api.BitFunction;
import pl.grzeslowski.jsupla.protocol.api.ChannelType;
import pl.grzeslowski.jsupla.protocol.api.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.channeltype.ChannelDescription;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.ChannelValue;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.GateValue;
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

    @Override
    public ChannelValue decode(
            final byte[] bytes, final int offset, final ChannelDescription description) {
        if (isGate(description)) {
            Preconditions.sizeMin(bytes, offset);
            final short value = INSTANCE.parseUnsignedByte(bytes, offset);
            if (value == 1) {
                return GateValue.OPEN;
            }
            if (value == 0) {
                return GateValue.CLOSE;
            }
            throw new IllegalArgumentException(
                    format("Don't know how to map value %s to OPEN/CLOSE!", value));
        }
        return decode(bytes, offset);
    }

    @Override
    public Class<? extends ChannelValue> getChannelValueType(final ChannelDescription description) {
        return isGate(description) ? GateValue.class : OnOffValue.class;
    }

    private boolean isGate(final ChannelDescription description) {
        return description != null
                && description.functions() != null
                && description.functions().stream()
                        .anyMatch(
                                function ->
                                        function == BitFunction.SUPLA_BIT_FUNC_CONTROLLINGTHEGATE
                                                || function
                                                        == BitFunction
                                                                .SUPLA_BIT_FUNC_CONTROLLINGTHEGARAGEDOOR
                                                || function
                                                        == BitFunction
                                                                .SUPLA_BIT_FUNC_ROLLER_GARAGE_DOOR);
    }
}
