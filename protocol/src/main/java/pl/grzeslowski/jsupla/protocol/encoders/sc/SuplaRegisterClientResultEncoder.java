package pl.grzeslowski.jsupla.protocol.encoders.sc;

import pl.grzeslowski.jsupla.protocol.encoders.PrimitiveEncoder;
import pl.grzeslowski.jsupla.protocol.structs.sc.SuplaRegisterClientResult;

public final class SuplaRegisterClientResultEncoder implements ServerClientEncoder<SuplaRegisterClientResult> {
    @SuppressWarnings("UnusedAssignment")
    @Override
    public byte[] encode(SuplaRegisterClientResult proto) {
        byte[] data = new byte[proto.size()];
        int offset = 0;

        offset += PrimitiveEncoder.writeInteger(proto.resultCode, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.clientId, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.locationCount, data, offset);
        offset += PrimitiveEncoder.writeInteger(proto.channelCount, data, offset);

        return data;
    }
}
