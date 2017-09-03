package pl.grzeslowski.jsupla.protocol.impl.decoders;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.TimevalDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.Timeval;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public class TimevalDecoderImpl implements TimevalDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public TimevalDecoderImpl(final PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public Timeval decode(final byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + Timeval.SIZE);

        final int seconds = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;
        final int milliseconds = primitiveDecoder.parseInt(bytes, offset);

        return new Timeval(seconds, milliseconds);
    }
}
