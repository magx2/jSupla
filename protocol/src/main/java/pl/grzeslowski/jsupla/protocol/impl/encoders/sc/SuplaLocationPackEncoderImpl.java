package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaLocationPackEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;

import static java.util.Objects.requireNonNull;

public final class SuplaLocationPackEncoderImpl implements SuplaLocationPackEncoder {
    private final PrimitiveEncoder primitiveEncoder;
    private final SuplaLocationEncoderImpl locationEncoder;

    public SuplaLocationPackEncoderImpl(PrimitiveEncoder primitiveEncoder, SuplaLocationEncoderImpl locationEncoder) {
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
