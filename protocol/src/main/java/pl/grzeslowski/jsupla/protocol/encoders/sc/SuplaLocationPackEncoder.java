package pl.grzeslowski.jsupla.protocol.encoders.sc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaLocationPack;

import static java.util.Objects.requireNonNull;

public final class SuplaLocationPackEncoder implements ServerClientEncoder<SuplaLocationPack> {
    private final PrimitiveEncoder primitiveEncoder;
    private final SuplaLocationEncoder locationEncoder;

    public SuplaLocationPackEncoder(PrimitiveEncoder primitiveEncoder, SuplaLocationEncoder locationEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
        this.locationEncoder = requireNonNull(locationEncoder);
    }

    @Override
    public byte[] encode(SuplaLocationPack proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.count, data, offset);
        offset += primitiveEncoder.writeInteger(proto.totalLeft, data, offset);
        for (SuplaLocation location : proto.locations) {
            final byte[] locationBytes = locationEncoder.encode(location);
            offset += primitiveEncoder.writeBytes(locationBytes, data, offset);
        }

        return data;
    }
}
