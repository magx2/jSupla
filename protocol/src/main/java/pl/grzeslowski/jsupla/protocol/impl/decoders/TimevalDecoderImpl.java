package pl.grzeslowski.jsupla.protocol.impl.decoders;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.TimevalDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public class TimevalDecoderImpl implements TimevalDecoder {
    public static final TimevalDecoderImpl INSTANCE = new TimevalDecoderImpl(PrimitiveDecoderImpl.INSTANCE);
    private final PrimitiveDecoder primitiveDecoder;

    public TimevalDecoderImpl(final PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaTimeval decode(final byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaTimeval.SIZE);

        final int seconds = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;
        final int milliseconds = primitiveDecoder.parseInt(bytes, offset);

        return new SuplaTimeval(seconds, milliseconds);
    }
}
