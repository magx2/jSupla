package pl.grzeslowski.jsupla.protocol.impl.decoders.sdc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sdc.SuplaVersionErrorDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sdc.SuplaVersionError;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;

public final class SuplaVersionErrorDecoderImpl implements SuplaVersionErrorDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaVersionErrorDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaVersionError decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaVersionError.SIZE);

        final short serverVersionMin = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short serverVersion = primitiveDecoder.parseUnsignedByte(bytes, offset);

        return new SuplaVersionError(serverVersionMin, serverVersion);
    }
}
