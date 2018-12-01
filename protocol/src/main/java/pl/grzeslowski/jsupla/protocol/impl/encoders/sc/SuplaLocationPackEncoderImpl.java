package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.api.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaLocationEncoder;
import pl.grzeslowski.jsupla.protocol.api.encoders.sc.SuplaLocationPackEncoder;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.api.structs.sc.SuplaLocationPack;
import pl.grzeslowski.jsupla.protocol.impl.encoders.PrimitiveEncoderImpl;

import static java.util.Objects.requireNonNull;

public final class SuplaLocationPackEncoderImpl implements SuplaLocationPackEncoder {
    public static final SuplaLocationPackEncoderImpl INSTANCE =
            new SuplaLocationPackEncoderImpl(PrimitiveEncoderImpl.INSTANCE, SuplaLocationEncoderImpl.INSTANCE);
    private final PrimitiveEncoder primitiveEncoder;
    private final SuplaLocationEncoder locationEncoder;

    SuplaLocationPackEncoderImpl(PrimitiveEncoder primitiveEncoder, SuplaLocationEncoder locationEncoder) {
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
