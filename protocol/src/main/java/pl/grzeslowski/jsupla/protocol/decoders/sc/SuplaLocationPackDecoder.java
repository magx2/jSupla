package pl.grzeslowski.jsupla.protocol.decoders.sc;

import pl.grzeslowski.jsupla.protocol.decoders.PrimitiveDecoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaLocationPack;

import static java.util.Objects.requireNonNull;
import static pl.grzeslowski.jsupla.protocol.consts.JavaConsts.INT_SIZE;

public final class SuplaLocationPackDecoder implements ServerClientDecoder<SuplaLocationPack> {
    private final SuplaLocationDecoder locationDecoder;

    public SuplaLocationPackDecoder(SuplaLocationDecoder locationDecoder) {
        this.locationDecoder = requireNonNull(locationDecoder);
    }

    @Override
    public SuplaLocationPack decode(byte[] bytes, int offset) {
        final int count = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final int totalLeft = PrimitiveDecoder.parseInt(bytes, offset);
        offset += INT_SIZE;

        final SuplaLocation[] locations = new SuplaLocation[count];
        for (int i = 0; i < count; i++) {
            locations[i] = locationDecoder.decode(bytes, offset);
            offset += locations[i].size();
        }

        return new SuplaLocationPack(count, totalLeft, locations);
    }
}
