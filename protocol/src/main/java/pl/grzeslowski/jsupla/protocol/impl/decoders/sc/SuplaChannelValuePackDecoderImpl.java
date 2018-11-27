package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelValueDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaChannelValuePackDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValue;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaChannelValuePack;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

public final class SuplaChannelValuePackDecoderImpl implements SuplaChannelValuePackDecoder {
    private final SuplaChannelValueDecoder suplaChannelValueDecoder;

    public SuplaChannelValuePackDecoderImpl(final SuplaChannelValueDecoder suplaChannelValueDecoder) {
        this.suplaChannelValueDecoder = requireNonNull(suplaChannelValueDecoder);
    }

    @Override
    public SuplaChannelValuePack decode(final byte[] bytes, int offset) {
        final int count = INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int totalLeft = INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final SuplaChannelValue[] items = new SuplaChannelValue[count];
        for (int i = 0; i < count; i++) {
            if (bytes.length - offset < SuplaChannelValue.SIZE) {
                throw new IllegalArgumentException(format(
                        "Can't parse SuplaChannelValue from byte array of length %s with offset %s, " +
                                "because length is %s!", bytes.length, offset, SuplaChannelValue.SIZE));
            }
            items[i] = suplaChannelValueDecoder.decode(bytes, offset);
            offset += SuplaChannelValue.SIZE;
        }

        return new SuplaChannelValuePack(
                count,
                totalLeft,
                items
        );
    }
}
