package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.sc.SuplaLocationDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaLocation;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class SuplaLocationDecoderImpl implements SuplaLocationDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaLocationDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaLocation decode(byte[] bytes, int offset) {
        final byte eol = primitiveDecoder.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final int id = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final long captionSize = primitiveDecoder.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] caption = Arrays.copyOfRange(bytes, offset, offset + (int) captionSize);

        return new SuplaLocation(eol, id, captionSize, caption);
    }
}
