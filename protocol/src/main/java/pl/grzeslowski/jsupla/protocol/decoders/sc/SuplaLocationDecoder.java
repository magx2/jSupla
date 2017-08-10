package pl.grzeslowski.jsupla.protocol.decoders.sc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveParser;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaLocation;

import java.util.Arrays;

import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.BYTE_SIZE;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public class SuplaLocationDecoder implements ServerClientDecoder<SuplaLocation> {
    @Override
    public SuplaLocation decode(byte[] bytes, int offset) {
        final byte eol = PrimitiveParser.parseByte(bytes, offset);
        offset += BYTE_SIZE;

        final int id = PrimitiveParser.parseInt(bytes, offset);
        offset += INT_SIZE;

        final long captionSize = PrimitiveParser.parseUnsignedInt(bytes, offset);
        offset += INT_SIZE;

        final byte[] caption = Arrays.copyOfRange(bytes, offset, offset + (int) captionSize);

        return new SuplaLocation(eol, id, captionSize, caption);
    }
}
