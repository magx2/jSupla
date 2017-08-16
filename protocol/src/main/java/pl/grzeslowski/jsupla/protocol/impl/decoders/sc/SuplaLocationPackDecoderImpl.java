package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.decoders.sc.ServerClientDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaLocationPack;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class SuplaLocationPackDecoderImpl implements ServerClientDecoder<SuplaLocationPack> {
    private final PrimitiveDecoder primitiveDecoder;
    private final SuplaLocationDecoderImpl locationDecoder;

    public SuplaLocationPackDecoderImpl(PrimitiveDecoder primitiveDecoder, SuplaLocationDecoderImpl locationDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
        this.locationDecoder = requireNonNull(locationDecoder);
    }

    @Override
    public SuplaLocationPack decode(byte[] bytes, int offset) {
        final int count = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int totalLeft = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final SuplaLocation[] locations = new SuplaLocation[count];
        for (int i = 0; i < count; i++) {
            locations[i] = locationDecoder.decode(bytes, offset);
            offset += locations[i].size();
        }

        return new SuplaLocationPack(count, totalLeft, locations);
    }
}
