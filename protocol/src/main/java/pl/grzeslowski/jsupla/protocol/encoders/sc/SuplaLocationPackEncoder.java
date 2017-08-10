package pl.grzeslowski.jsupla.protocol.encoders.sc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaLocation;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaLocationPack;

import static java.util.Objects.requireNonNull;

public class SuplaLocationPackEncoder implements ServerClientEncoder<SuplaLocationPack> {
    private final SuplaLocationEncoder locationEncoder;

    public SuplaLocationPackEncoder(SuplaLocationEncoder locationEncoder) {
        this.locationEncoder = requireNonNull(locationEncoder);
    }

    @Override
    public byte[] encode(SuplaLocationPack proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeInteger(proto.count, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.totalLeft, data, offset);
        for (SuplaLocation location : proto.locations) {
            final byte[] locationBytes = locationEncoder.encode(location);
            offset += PrimitiveEncoder.writeBytes(locationBytes, data, offset);
        }

        return data;
    }
}
