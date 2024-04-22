package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaLocationDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaLocationDecoderImpl implements SuplaLocationDecoder {
    public static final SuplaLocationDecoderImpl INSTANCE = new SuplaLocationDecoderImpl();

    @Override
    public SuplaLocation decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaLocation.MIN_SIZE);

        final byte eol = PrimitiveDecoderImpl.INSTANCE.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final int id = PrimitiveDecoderImpl.INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final long captionSize = PrimitiveDecoderImpl.INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        Preconditions.sizeMin(bytes, offset + (int) captionSize);
        final byte[] caption = PrimitiveDecoderImpl.INSTANCE.copyOfRange(bytes, offset, offset + (int) captionSize);

        return new SuplaLocation(eol, id, captionSize, caption);
    }
}
