package pl.grzeslowski.jsupla.protocol.impl.channeltypes.ds;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.channeltypes.ds.RelayTypeChannelDecoder;
import pl.grzeslowski.jsupla.protocol.api.channelvalues.OnOff;

import static java.lang.String.format;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

public class RelayTypeChannelDecoderImpl implements RelayTypeChannelDecoder {
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
