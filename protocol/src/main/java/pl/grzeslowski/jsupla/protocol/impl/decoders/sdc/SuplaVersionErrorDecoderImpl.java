package pl.grzeslowski.jsupla.protocol.impl.decoders.sdc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.sdc.SuplaVersionErrorDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sdc.SuplaVersionError;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;

public final class SuplaVersionErrorDecoderImpl implements SuplaVersionErrorDecoder {
    private final PrimitiveDecoder primitiveDecoder;

    public SuplaVersionErrorDecoderImpl(PrimitiveDecoder primitiveDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
    }

    @Override
    public SuplaVersionError decode(byte[] bytes, int offset) {
        final short serverVersionMin = primitiveDecoder.parseUnsignedByte(bytes, offset);
        offset += BYTE_SIZE;

        final short serverVersion = primitiveDecoder.parseUnsignedByte(bytes, offset);

        return new SuplaVersionError(serverVersionMin, serverVersion);
    }
}
