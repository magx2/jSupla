package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaLocationDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;

import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;
import static pl.grzeslowski.jsupla.protocol.impl.decoders.PrimitiveDecoderImpl.INSTANCE;

public final class SuplaLocationDecoderImpl implements SuplaLocationDecoder {
    @Override
    public SuplaLocation decode(byte[] bytes, int offset) {
        Preconditions.sizeMin(bytes, offset + SuplaLocation.MIN_SIZE);

        final byte eol = INSTANCE.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final int id = INSTANCE.parseInt(bytes, offset);
        offset += INT_SIZE;

        final long captionSize = INSTANCE.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        Preconditions.sizeMin(bytes, offset + (int) captionSize);
        final byte[] caption = INSTANCE.copyOfRange(bytes, offset, offset + (int) captionSize);

        return new SuplaLocation(eol, id, captionSize, caption);
    }
}
