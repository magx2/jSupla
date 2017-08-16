package pl.grzeslowski.jsupla.protocol.encoders.dcs;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.dcs.SuplaSetActivityTimeout;

import static java.util.Objects.requireNonNull;

public final class SuplaSetActivityTimeoutEncoder implements DeviceClientServerEncoder<SuplaSetActivityTimeout> {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaSetActivityTimeoutEncoder(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @Override
    public byte[] encode(SuplaSetActivityTimeout proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        primitiveEncoder.writeUnsignedByte(proto.activityTimeout, data, offset);

        return data;
    }
}
