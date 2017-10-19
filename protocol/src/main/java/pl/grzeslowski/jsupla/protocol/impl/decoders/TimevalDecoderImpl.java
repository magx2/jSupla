package pl.grzeslowski.jsupla.protocol.impl.decoders;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.TimevalDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.LONG_SIZE;

public class TimevalDecoderImpl implements TimevalDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public TimevalDecoderImpl(final PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaTimeval decode(final byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaTimeval.SIZE);

        final long seconds = primitiveDecoder.parseLong(bytes, offset);
        offset += LONG_SIZE;
        final long milliseconds = primitiveDecoder.parseLong(bytes, offset);

        return new SuplaTimeval(seconds, milliseconds);
    }
}
