package pl.grzeslowski.jsupla.protocol.api.channeltype.decoders;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.channeltype.value.OnOff;
import pl.grzeslowski.jsupla.protocol.api.decoders.Decoder;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

class RelayTypeChannelDecoderImpl implements Decoder<OnOff> {
    @Override
    public OnOff decode(final byte[] bytes, final int offset) {
        Preconditions.sizeMin(bytes, offset);
        final short value = INSTANCE.parseUnsignedByte(bytes, offset);
        if (value == 1) {
            return OnOff.ON;
        }
        if (value == 0) {
            return OnOff.OFF;
        }
        throw new IllegalArgumentException(format("Don't know how to map value %s to ON/OFF!", value));
    }
}
