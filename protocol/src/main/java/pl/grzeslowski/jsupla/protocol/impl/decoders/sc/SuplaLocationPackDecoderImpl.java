package pl.grzeslowski.jsupla.protocol.impl.decoders.sc;

import pl.grzeslowski.jsupla.Preconditions;
import pl.grzeslowski.jsupla.protocol.api.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaLocationDecoder;
import pl.grzeslowski.jsupla.protocol.api.decoders.sc.SuplaLocationPackDecoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.api.consts.JavaConsts.INT_SIZE;

public final class SuplaLocationPackDecoderImpl implements SuplaLocationPackDecoder {
    private final PrimitiveDecoder primitiveDecoder;
    private final SuplaLocationDecoder locationDecoder;

    public SuplaLocationPackDecoderImpl(PrimitiveDecoder primitiveDecoder, SuplaLocationDecoder locationDecoder) {
        this.primitiveDecoder = requireNonNull(primitiveDecoder);
        this.locationDecoder = requireNonNull(locationDecoder);
    }

    @Override
    public SuplaLocationPack decode(byte[] bytes, int offset) {
        Preconditions.checkMinArrayLength(bytes, offset + SuplaLocationPack.MIN_SIZE);

        final int count = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int totalLeft = primitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        Preconditions.checkMinArrayLength(bytes,
                offset + SuplaLocationPack.MIN_SIZE + SuplaLocation.MIN_SIZE * count);
        final SuplaLocation[] locations = new SuplaLocation[count];
        for (int i = 0; i < count; i++) {
            locations[i] = locationDecoder.decode(bytes, offset);
            offset += locations[i].size();
        }

        return new SuplaLocationPack(count, totalLeft, locations);
    }
}
