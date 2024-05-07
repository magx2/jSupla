package pl.grzeslowski.jsupla.protocol.api.decoders;

import lombok.val;
import pl.grzeslowski.jsupla.protocol.api.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.structs.SuplaTimeval;

import static pl.grzeslowski.jsupla.protocol.api.JavaConsts.INT_SIZE;

public class SuplaTimevalDecoder implements ProtoWithSizeDecoder<SuplaTimeval> {
    public static final SuplaTimevalDecoder INSTANCE = new SuplaTimevalDecoder();

    @Override
    public SuplaTimeval decode(final byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaTimeval.SIZE);

        val seconds = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;
        final int milliseconds = PrimitiveDecoder.INSTANCE.parseInt(bytes, offset);

        return new SuplaTimeval(seconds, milliseconds);
    }
}
