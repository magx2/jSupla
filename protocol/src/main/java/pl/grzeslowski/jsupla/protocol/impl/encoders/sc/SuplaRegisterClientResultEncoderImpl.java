package pl.grzeslowski.jsupla.protocol.impl.encoders.sc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.encoders.sc.ServerClientEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaRegisterClientResult;

import static java.util.Objects.requireNonNull;

public final class SuplaRegisterClientResultEncoderImpl implements ServerClientEncoder<SuplaRegisterClientResult> {
    private final PrimitiveEncoder primitiveEncoder;

    public SuplaRegisterClientResultEncoderImpl(PrimitiveEncoder primitiveEncoder) {
        this.primitiveEncoder = requireNonNull(primitiveEncoder);
    }

    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaRegisterClientResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += primitiveEncoder.writeInteger(proto.resultCode, data, offset);
        offset += primitiveEncoder.writeInteger(proto.clientId, data, offset);
        offset += primitiveEncoder.writeInteger(proto.locationCount, data, offset);
        offset += primitiveEncoder.writeInteger(proto.channelCount, data, offset);

        return data;
    }
}
